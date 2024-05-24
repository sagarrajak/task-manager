package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
