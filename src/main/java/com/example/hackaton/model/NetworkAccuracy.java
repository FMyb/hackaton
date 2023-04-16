package com.example.hackaton.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Yaroslav Ilin
 */
@Entity(name = "accuracy")
@Table(name = "accuracy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(allocationSize = 1, name = "id_seq", sequenceName = "id_seq")
public class NetworkAccuracy {
    @Id
    @GeneratedValue(generator = "id_seq")
    private long eventId;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "type")
    private String type;

    @Column(name = "start_timestamp")
    private long startTimestamp;

    @Column(name = "finish_timestamp")
    private long finishTimestamp;

    @Column(name = "bytes_count")
    private long bytesCount;


}
