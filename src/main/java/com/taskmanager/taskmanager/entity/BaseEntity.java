package com.taskmanager.taskmanager.entity;

import jakarta.persistence.Column;

import java.util.Date;

public class BaseEntity {
    @Column(name = "created_at", nullable = false)
    Date created_at;

    @Column(name = "updated_at", nullable = false)
    Date updated_at;
}
