package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsEntityRepositor extends JpaRepository<CommentsEntity, Long> {
}