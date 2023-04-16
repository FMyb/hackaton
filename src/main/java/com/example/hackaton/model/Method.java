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
@SequenceGenerator(allocationSize = 1, name = "id_seq", sequenceName = "id_seq")
public class Method {
    @Id
    @GeneratedValue(generator = "id_seq")
    private Long methodId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "name")
    private String name;
}
