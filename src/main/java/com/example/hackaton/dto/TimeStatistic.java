package com.example.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TimeStatistic {
	private final String methodName;

	private final long methodId;

	private final Double avgBefore;

	private final Double avgAfter;

	private final Double percent;

}
