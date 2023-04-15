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
@Entity(name = "archive_state")
@Table(name = "archive_states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveState {
    @Id
    private UUID archiveStateId;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private Method method;

    @Column(name = "avg_byte_count")
    private long avgByteCount;

    @Column(name = "avg_time")
    private long avgTime;

    @Column(name = "timestamp")
    private long timestamp;
}
