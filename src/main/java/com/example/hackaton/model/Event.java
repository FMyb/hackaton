package com.example.hackaton.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */
@Entity(name = "event")
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private UUID eventId;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private Method method;

    @Column(name = "start_timestamp")
    private long startTimestamp;

    @Column(name = "finish_timestamp")
    private long finishTimestamp;

    @Column(name = "bytes_count")
    private long bytesCount;

    @Column(name = "stacktrace")
    private String stacktrace;
}
