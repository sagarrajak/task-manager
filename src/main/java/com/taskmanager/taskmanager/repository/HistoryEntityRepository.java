package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryEntityRepository extends JpaRepository<HistoryEntity, Long> {
}