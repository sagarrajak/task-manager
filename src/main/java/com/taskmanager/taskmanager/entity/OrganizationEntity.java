package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import javax.swing.text.html.HTML;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "organization")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "org_unique_name", nullable = false, length = 25, unique = true)
    @Pattern(regexp = "^\\S*$")
    String orgId;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<BoardEntity> boards;

    @OneToMany(mappedBy = "organization" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<StepsEntity> steps;

    @OneToMany(mappedBy = "organization" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<TagsEntity> tags;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "organization_user",
            joinColumns = @JoinColumn(name = "org_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<UserEntity> users;

}
