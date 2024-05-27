package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrganizationRepositoryTest {
        @Autowired
        TestEntityManager manager;

        @Autowired
        OrganizationRepository repositoryToTest;
        OrganizationEntity organization;
        OrganizationEntity organization2;
        OrganizationEntity organization3;

        @BeforeEach()
        void setup() {
                organization = new OrganizationEntity();
                organization2 = new OrganizationEntity();
                organization3 = new OrganizationEntity();

                organization.setName("Testorg");
                organization.setDescription("This is fake org");
                organization.setOrgId("testOrg");

                organization2.setName("Testorg");
                organization2.setDescription("This is fake org");
                organization2.setOrgId("testOrg2");

                organization3.setName("Testorg");
                organization3.setDescription("This is fake org");
                organization3.setOrgId("testOrg3");

                organization3.setName("Testorg");
                organization3.setDescription("This is fake org");
                organization3.setOrgId("testOrg4");

                this.manager.persistAndFlush(organization);
                this.manager.persistAndFlush(organization2);
                this.manager.persistAndFlush(organization3);
        }

        @DisplayName("Its should find all organization with same case")
        @Test
        void existsByOrgIdIgnoreCaseTest() {
                Optional<OrganizationEntity> oneByOrgId =
                        this.repositoryToTest.findOneByOrgIdIgnoreCase(this.organization.getOrgId().toLowerCase());
                Assertions.assertTrue(oneByOrgId.isPresent(), "It shoud return true");
                Assertions.assertEquals(oneByOrgId.get().getOrgId(), this.organization.getOrgId(), "Org id must be equal");
        }
}