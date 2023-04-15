package com.example.hackaton;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Scheduler {
	private final ScheduledThreadPoolExecutor executor;
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
		System.out.println(1);
	}
}