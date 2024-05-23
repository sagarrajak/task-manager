package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "task_name", nullable = false)
    String name;

    @Column(name = "description", length = 1000, nullable = false)
    String description;

    @Column(name = "story_point")
    float storyPoint;

    @Column(name = "start_date")
    LocalDateTime startDateTime;

    @Column(name = "end_date")
    LocalDateTime endDateTime;

    @Column(name="is_story")
    boolean isStory = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    BoardEntity board;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "step_id")
    StepsEntity steps;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_task")
    TaskEntity parentTask;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
    Set<CommentsEntity> comments;
}
