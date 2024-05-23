package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
