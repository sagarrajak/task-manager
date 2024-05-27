package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.PermissionEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.entity.UserOrganizationTable;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserOrganizationTableRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    private OrganizationEntity organization;
    private OrganizationEntity organization2;
    private UserEntity user;
    private UserEntity user2;
    private UserEntity user3;


    @Autowired
    private UserOrganizationTableRepository userOrganizationTableRepository;


    private void saveUserOrg(UserEntity user, OrganizationEntity org) {
        UserOrganizationTable userOrganization;
        userOrganization = new UserOrganizationTable();
        userOrganization.setOrganization(org);
        userOrganization.setUser(user);

       this.entityManager.persistAndFlush(userOrganization);
    }

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Testorg");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");
        organization = this.entityManager.persistAndFlush(organization);


        organization2 = new OrganizationEntity();
        organization2.setName("Testorg2");
        organization2.setDescription("This is fake org");
        organization2.setOrgId("testOrg2");
        organization2 = this.entityManager.persistAndFlush(organization2);

        user = new UserEntity();
        user.setEmail("sagarrajak858@gmail.com");
        user.setPassword("sagar123");
        user.setFirstName("sagar");
        user.setLastName("rajak");
        user = this.entityManager.persistAndFlush(user);

        user2 = new UserEntity();
        user2.setEmail("sagarrajak859@gmail.com");
        user2.setPassword("sagar123");
        user2.setFirstName("sagar");
        user2.setLastName("rajak");
        user2 = this.entityManager.persistAndFlush(user2);

        user3 = new UserEntity();
        user3.setEmail("sagarrajak857@gmail.com");
        user3.setPassword("sagar123");
        user3.setFirstName("sagar");
        user3.setLastName("rajak");
        user3 = this.entityManager.persistAndFlush(user3);

        this.saveUserOrg(user, organization);
        this.saveUserOrg(user2, organization);
        this.saveUserOrg(user3, organization);

        this.saveUserOrg(user, organization2);
        this.saveUserOrg(user2, organization2);
    }

    @Test()
    void testFindUserOfGivenOrg() {
        List<UserEntity> users = this.userOrganizationTableRepository.findUserOfGivenOrg("testOrg");
        assertNotNull(users);
        System.out.println(users);
        assertEquals(3, users.size());
        HashSet<Long> ids = new HashSet<>(List.of(user.getId(), user2.getId(), user3.getId()));
        for (var user: users) {
            Long id = user.getId();
            Assertions.assertTrue(ids.contains(id));
        }
    }

    @Test()
    void testCheckIfUserAlreadyExistInOrg_ifUserAlreadyAddedInOrg_itShouldReturnTrue() {
        boolean isExist = this.userOrganizationTableRepository.checkIfUserAlreadyExistInOrg(organization.getOrgId(), user.getEmail());
        boolean isNotExist = this.userOrganizationTableRepository.checkIfUserAlreadyExistInOrg(organization2.getOrgId(), user3.getEmail());
        assertTrue(isExist);
        assertFalse(isNotExist);
    }


}