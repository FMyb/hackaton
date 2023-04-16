package com.example.hackaton.service;

import com.example.hackaton.model.ArchiveState;
import com.example.hackaton.model.Event;
import com.example.hackaton.model.Method;
import com.example.hackaton.repository.ArchiveStateRepository;
import com.example.hackaton.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArchiveService {

	private final EventRepository eventStateRepository;

	private final ArchiveStateRepository archiveStateRepository;

	public boolean archiveData(long tsBefore, long tsAfter) {
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

					return new ArchiveState(UUID.randomUUID(), method, avgByteCount, avgTime, timestamp);
				})
				.toList();

		archiveStateRepository.saveAll(archiveStates);

		return true;
	}
}
