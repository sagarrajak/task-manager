package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks")
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "step_id", nullable = false)
    StepsEntity steps;

    @Column(name = "story_point")
    float storyPoint;

    @Column(name = "start_date")
    LocalDateTime startDateTime;

    @Column(name = "end_date")
    LocalDateTime endDateTime;

    @Column(name="is_story")
    boolean isStory = false;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "tasks_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<TagsEntity> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_task")
    TaskEntity parentTask;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
    Set<CommentsEntity> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
    Set<HistoryEntity> history;
}
