package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "organization")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tasks extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name",  nullable = false)
    String name;

    @Column(name = "description")
    String description;
}
