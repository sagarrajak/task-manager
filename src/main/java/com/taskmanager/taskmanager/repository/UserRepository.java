package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.BaseEntity;
import com.taskmanager.taskmanager.entity.CommentsEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
