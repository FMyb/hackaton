package com.example.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yaroslav Ilin
 */

@AllArgsConstructor
@Getter
public class EventDto {

    private long eventId;

    private long methodId;

    private String fullName;

    private String name;

    private long startTimestamp;

    private long finishTimestamp;

    private long bytesCount;

    private String stacktrace;

}
