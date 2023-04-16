package com.example.hackaton.scheduler;

import com.example.hackaton.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Scheduler {
	private final ScheduledThreadPoolExecutor executor;

	private StatisticService archiveService;
	private final long updateDelaySeconds = 900;

	@Autowired
	public Scheduler(StatisticService archiveService) {
		this.archiveService = archiveService;
		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(
				this::run,
				0,
				updateDelaySeconds,
				TimeUnit.SECONDS
		);
	}


	private void run() {
		long now = LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli();
		now = now - now % (updateDelaySeconds * 1000);
		archiveService.archiveData(
				now,
				now + updateDelaySeconds * 1000
				);
	}
}