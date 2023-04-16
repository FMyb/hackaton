package com.example.hackaton.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Yaroslav Ilin
 */
@Entity(name = "archive_state")
@Table(name = "archive_states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchiveState {
    @Id
    @GeneratedValue
    private long archiveStateId;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private Method method;

    @Column(name = "avg_byte_count")
    private double avgByteCount;

    @Column(name = "avg_time")
    private double avgTime;

    @Column(name = "timestamp")
    private long timestamp;
}
