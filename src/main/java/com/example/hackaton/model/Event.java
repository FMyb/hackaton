package com.example.hackaton.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Yaroslav Ilin
 */
@Entity(name = "event")
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(allocationSize = 1, name = "id_seq", sequenceName = "id_seq")
public class Event {
    @Id
    @GeneratedValue(generator = "id_seq")
    private long eventId;

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
