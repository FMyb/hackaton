package com.example.hackaton.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Yaroslav Ilin
 */

@Entity(name = "method")
@Table(name = "methods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Method {
    @Id
    @GeneratedValue
    private long methodId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "name")
    private String name;
}
