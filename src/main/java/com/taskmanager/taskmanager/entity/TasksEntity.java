package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "organization")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name",  nullable = false)
    String name;

    @Column(name = "description")
    String description;
}
