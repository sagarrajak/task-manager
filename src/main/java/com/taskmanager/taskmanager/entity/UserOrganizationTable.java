package com.taskmanager.taskmanager.entity;

import com.taskmanager.taskmanager.exception.OrganizationException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_organization")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrganizationTable  {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false)
    OrganizationEntity organization;

    // store user permission in organization
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_id")
    PermissionEntity permission;
}
