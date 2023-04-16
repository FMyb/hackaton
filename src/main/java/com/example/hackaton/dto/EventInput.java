package com.example.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventInput {
	private String fullMethodName;

	private String methodName;

	private long startTimestamp;

	private long finishTimestamp;

	private long bytesCount;

	private String stacktrace;
}
