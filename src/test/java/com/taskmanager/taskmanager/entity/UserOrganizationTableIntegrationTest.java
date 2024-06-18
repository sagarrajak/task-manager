package com.taskmanager.taskmanager.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserOrganizationTableIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    private OrganizationEntity organization;
    private UserEntity user;

    private PermissionEntity permission;

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Testorg");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");
        organization = this.entityManager.persistAndFlush(organization);

        user = new UserEntity();
        user.setEmail("sagarrajak858@gmail.com");
        user.setPassword("sagar123");
        user.setFirstName("sagar");
        user.setLastName("rajak");
        user = this.entityManager.persistAndFlush(user);

        permission = new PermissionEntity();
    }

    @DisplayName("It should save a organization task")
    @Test
    void savePermission() {
        UserOrganizationTable userOrganization = new UserOrganizationTable();
        userOrganization.setPermission(permission);
        userOrganization.setOrganization(organization);
        userOrganization.setUser(user);
        var savedUserOrganization = this.entityManager.persistAndFlush(userOrganization);
        Assertions.assertNotNull(savedUserOrganization);
    }


}