package com.example.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */

@AllArgsConstructor
@Getter
public class EventDto {

    private UUID eventId;

    private UUID methodId;

    private String fullName;

    private String name;

    private long startTimestamp;

    private long finishTimestamp;

    private long bytesCount;

    private String stacktrace;

}
