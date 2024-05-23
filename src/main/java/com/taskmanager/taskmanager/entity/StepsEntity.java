package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "steps")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepsEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 1000)
    String name;

    @Column(name = "description", length = 1000)
    String description;

    @Column(name = "color", nullable = false, length = 7)
    String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", nullable = false)
    OrganizationEntity organization;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", nullable = false)
    BoardEntity boardId;

    @Column(name = "task_order", nullable = false)
    Integer order;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "steps"
    )
    Set<TaskEntity> tasks;
}
