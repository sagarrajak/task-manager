package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsEntityRepository extends JpaRepository<TagsEntity, Long> {
}