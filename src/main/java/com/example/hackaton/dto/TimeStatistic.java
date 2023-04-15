package com.example.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TimeStatistic {
	private final String methodName;

	private final Integer methodId;

	private final Double avgBefore;

	private final Double avgCurrent;

	private final Double percent;

}
