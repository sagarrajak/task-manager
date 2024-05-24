package com.taskmanager.taskmanager.entity;

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
}
