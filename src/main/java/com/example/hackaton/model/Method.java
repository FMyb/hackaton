package com.example.hackaton.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Yaroslav Ilin
 */

@Entity(name = "method")
@Table(name = "methods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Method {
    @Id
    private UUID methodId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "name")
    private String name;
}
