package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends
        JpaRepository<OrganizationEntity, Long>{
    Optional<OrganizationEntity> findOneByOrgIdIgnoreCase(String orgId);

    @Query("""
            select
                case when (count(oe) > 0) then true
                else false
                end
            from OrganizationEntity oe
            where oe.orgId ilike :orgId
            """)
    boolean isThereOrganizationWithSameName(String orgId);
}
