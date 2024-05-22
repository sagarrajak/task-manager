package com.taskmanager.taskmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {
    @Column(name = "task_name", nullable = false)
    String name;

    @Column(name = "description", length = 1000, nullable = false)
    String description;

    @Column(name = "story_point")
    float storyPoint;

    LocalDateTime startDateTime;

    LocalDateTime endDateTime;


}
