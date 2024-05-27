package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.entity.UserOrganizationTable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface UserOrganizationTableRepository extends JpaRepository<UserOrganizationTable, Long> {

    @Query(value = "select u from UserOrganizationTable uo join uo.user u where uo.organization.orgId ilike :id ")
    List<UserEntity> findUserOfGivenOrg(String id);


    @Query("""
            select
                case when (count(u) > 0) then true
                else false
                end
            from UserOrganizationTable uo join uo.user u
            where uo.organization.orgId ilike :orgId and u.email ilike :email
            """)
    boolean checkIfUserAlreadyExistInOrg(String orgId, String email);

}
