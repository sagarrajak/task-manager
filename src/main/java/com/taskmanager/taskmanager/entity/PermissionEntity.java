package com.taskmanager.taskmanager.entity;

import com.taskmanager.taskmanager.entity.enums.AccessTypeEnum;
import com.taskmanager.taskmanager.entity.enums.ResourcesTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permission")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description", length = 10000)
    String description;

    @Column(name = "access_type", nullable = false, columnDefinition = "VARCHAR(256)")
    @Enumerated(EnumType.STRING)
    AccessTypeEnum accessType;

    @Column(name = "resources_type", nullable = false, columnDefinition = "VARCHAR(256)")
    @Enumerated(EnumType.STRING)
    ResourcesTypeEnum resourcesType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    OrganizationEntity organization;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    TaskEntity task;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    TaskEntity board;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_task_id")
    TaskEntity comment_task_id;
}
