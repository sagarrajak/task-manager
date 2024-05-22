package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "board")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity extends BaseEntity {
    @Column(name = "boardUniqueId", nullable = false, unique = true, length = 25)
    @Pattern(regexp = "^\\S*$")
    String boardUniqueId;

    @Column(name = "name", nullable = false)
    String name;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;
}
