package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionEntityRepository extends JpaRepository<PermissionEntity, Long> {
}