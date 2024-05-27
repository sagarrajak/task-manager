package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {
}