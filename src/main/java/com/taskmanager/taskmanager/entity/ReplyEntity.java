package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "comment", nullable = false, length = 10000)
    String comment;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_comment", nullable = false)
    CommentsEntity parentComment;
}
