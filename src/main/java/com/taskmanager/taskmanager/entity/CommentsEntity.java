package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "comment", nullable = false, length = 10000)
    String comment;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", nullable = false)
    TaskEntity task;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "parentComment"
    )
    Set<CommentsEntity> commentsEntity;
}
