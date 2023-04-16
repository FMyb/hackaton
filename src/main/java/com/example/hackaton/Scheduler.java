package com.example.hackaton;

import com.example.hackaton.service.ArchiveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Scheduler {
	private final ScheduledThreadPoolExecutor executor;

	@Autowired
	private ArchiveService archiveService;
	private final long updateDelaySeconds = 900;

	public Scheduler() {
		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(
				this::run,
				0,
				updateDelaySeconds,
				TimeUnit.SECONDS
		);
	}


	private void run() {
		archiveService.archiveData(
				LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toEpochSecond() - updateDelaySeconds,
				LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli()
				);
	}
}