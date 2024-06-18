package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.StepsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepsEntityRepository extends JpaRepository<StepsEntity, Long> {
}