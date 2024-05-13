package com.taskmanager.taskmanager.entity;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Column(name = "created_at", nullable = false)
    Date created_at;

    @Column(name = "updated_at", nullable = false)
    Date updated_at;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

}
