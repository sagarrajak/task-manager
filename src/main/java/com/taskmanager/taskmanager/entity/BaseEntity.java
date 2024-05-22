package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@ToString
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    LocalDateTime updated_at;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

    @Version
    private Integer version;
}
