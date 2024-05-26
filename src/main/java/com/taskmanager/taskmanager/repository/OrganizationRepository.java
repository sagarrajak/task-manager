package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends
        JpaRepository<OrganizationEntity, Long>{
    Optional<OrganizationEntity> findOneByOrgId(String orgId);
}
