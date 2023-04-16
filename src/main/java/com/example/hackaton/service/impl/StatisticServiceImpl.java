package com.example.hackaton.service.impl;

import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.EventInput;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.model.Event;
import com.example.hackaton.model.Method;
import com.example.hackaton.repository.ArchiveStateRepository;
import com.example.hackaton.repository.EventRepository;
import com.example.hackaton.repository.MethodRepository;
import com.example.hackaton.service.StatisticService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Ilin
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    private final ArchiveStateRepository archiveStateRepository;

    private final EventRepository eventStateRepository;

    private final MethodRepository methodRepository;

    public StatisticServiceImpl(
            ArchiveStateRepository archiveStateRepository,
            EventRepository eventStateRepository, MethodRepository methodRepository) {
        this.archiveStateRepository = archiveStateRepository;
        this.eventStateRepository = eventStateRepository;
        this.methodRepository = methodRepository;
    }

    @Override
    public List<TimeStatistic> getTimeStatistics(long tsBefore, long tsAfter, Function<ArchiveState, Double> avg, Integer limit) {
        List<ArchiveState> archiveStatesBefore = archiveStateRepository.findAllByTimestamp(tsBefore);
        Map<Long, ArchiveState> archiveStatesAfter = archiveStateRepository.findAllByTimestamp(tsAfter).stream()
                .collect(Collectors.toMap(x -> x.getMethod().getMethodId(), x -> x));
        List<TimeStatistic> result = new ArrayList<>();
        for (var before : archiveStatesBefore) {
            ArchiveState after = archiveStatesAfter.get(before.getMethod().getMethodId());
            if (after == null) {
                continue;
            }
            double avgBefore = avg.apply(before);
            if (Math.abs(avgBefore) < 0.0000001) {
                continue;
            }
            double avgAfter = avg.apply(after);
            TimeStatistic timeStatistic = new TimeStatistic(
                    before.getMethod().getName(),
                    before.getMethod().getMethodId(),
                    avgBefore,
                    avgAfter,
                    avgAfter / avgBefore
            );
            result.add(timeStatistic);
        }
        result.sort((x, y) -> y.getPercent().compareTo(x.getPercent()));
        return result.subList(0, Math.min(result.size(), limit));
    }

    @Override
    public List<Point> getDependencyData(long id, Function<ArchiveState, Double> avg, long tsBefore, long tsAfter) {
        return archiveStateRepository.findAllByMethod_MethodIdAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestamp(
                id,
                tsBefore,
                tsAfter
        ).stream().map(it -> new Point(it.getTimestamp(), avg.apply(it))).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getStac(long id, Function<ArchiveState, Double> avg, long tsBefore, long tsAfter) {
        return null;
    }

    @Override
    @Transactional
    public void saveStats(List<EventInput> inputs) {
        for (EventInput input : inputs) {
            Method method = methodRepository.findMethodByFullName(input.getFullMethodName());
            if (method == null) {
                method = methodRepository.save(Method.builder()
                        .fullName(input.getFullMethodName())
                        .name(input.getMethodName())
                        .build()
                );
            }
            eventStateRepository.save(Event.builder()
                    .method(method)
                    .startTimestamp(input.getStartTimestamp())
                    .finishTimestamp(input.getFinishTimestamp())
                    .bytesCount(input.getBytesCount())
                    .stacktrace(input.getStacktrace())
                    .build()
            );
        }
    }

    @Override
    @Transactional
    public void archiveData(long tsBefore, long tsAfter) {
        List<Event> events = eventStateRepository.getStatesForArchive(tsBefore, tsAfter);

        List<ArchiveState> archiveStates = events.stream()
                .collect(Collectors.groupingBy(Event::getMethod))
                .entrySet().stream()
                .map(entry -> {
                    Method method = entry.getKey();
                    List<Event> methodEvents = entry.getValue();

                    double avgByteCount = methodEvents.stream()
                            .mapToLong(Event::getBytesCount)
                            .average()
                            .orElse(0);

                    double avgTime = methodEvents.stream()
                            .mapToDouble(event -> (double) (event.getFinishTimestamp() - event.getStartTimestamp()))
                            .average()
                            .orElse(0);

                    long fifteenMinutes = 15 * 60 * 1000; // 15 minutes in milliseconds

                    long timestamp = methodEvents.stream()
                            .mapToLong(Event::getFinishTimestamp)
                            .map(
                                    t -> t - (t % fifteenMinutes)
                            ).findAny().orElse(0);

                    return ArchiveState.builder()
                            .method(method)
                            .avgByteCount(avgByteCount)
                            .avgTime(avgTime)
                            .timestamp(timestamp)
                            .build();
                })
                .toList();

        archiveStateRepository.saveAll(archiveStates);

    }
}
