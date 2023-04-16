package com.example.hackaton.service.impl;

import com.example.hackaton.dto.EventDto;
import com.example.hackaton.dto.Point;
import com.example.hackaton.dto.TimeStatistic;
import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.model.Event;
import com.example.hackaton.model.Method;
import com.example.hackaton.repository.ArchiveStateRepository;
import com.example.hackaton.repository.EventRepository;
import com.example.hackaton.service.StatisticService;
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

    public StatisticServiceImpl(
            ArchiveStateRepository archiveStateRepository) {
        this.archiveStateRepository = archiveStateRepository;
    }

    @Override
    public List<TimeStatistic> getTimeStatistics(long tsBefore, long tsAfter, Function<ArchiveState, Double> avg, Integer limit) {
        List<ArchiveState> archiveStatesBefore = archiveStateRepository.findAllByTimestamp(tsBefore);
        Map<UUID, ArchiveState> archiveStatesAfter = archiveStateRepository.findAllByTimestamp(tsAfter).stream()
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
        return result;
    }

    @Override
    public List<Point> getDependencyData(long id, String type, long tsBefore, long tsAfter) {
        return null;
    }

    @Override
    public List<EventDto> getStac(long id, String type, long tsBefore, long tsAfter) {
        return null;
    }

}
