package com.taskmanager.taskmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserOrganizationTableRepositoryTest {

    private OrganizationEntity organization;
    private UserEntity user1;
    private UserEntity user2;
    private RoleEntity role;
    private PermissionEntity permission;

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Testorg");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");

        user1 = new UserEntity();
        user1.setEmail("sagarrajak858@gmail.com");
        user1.setPassword("sagar123");
        user1.setFirstName("sagar");
        user1.setLastName("rajak");

        user2 = new UserEntity();
        user2.setEmail("sagarrajak859@gmail.com");
        user2.setPassword("sagar123");
        user2.setFirstName("sagar");
        user2.setLastName("rajak");

        permission = new PermissionEntity();
    }
}