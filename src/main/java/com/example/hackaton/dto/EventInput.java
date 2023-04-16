package com.example.hackaton.dto;

import com.example.hackaton.model.Method;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

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
