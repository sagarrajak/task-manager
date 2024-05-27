package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.entity.UserOrganizationTable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserOrganizationTableRepository extends JpaRepository<UserOrganizationTable, Long> {
    @Query(value = "select u from Users u where u.id in (select uot.user.id from UserOrganizationTable uot  where uot.organization_id = :id)", nativeQuery = true)
    UserEntity findUserOfGivenOrg(String id);



}
