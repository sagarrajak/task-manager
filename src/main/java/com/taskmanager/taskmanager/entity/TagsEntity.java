package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tags")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagsEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 25, unique = true)
    String name;

    @Column(name = "color", length = 7)
    String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    OrganizationEntity organization;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tags")
    Set<TaskEntity> taskEntities;
}
