package com.taskmanager.taskmanager.entity;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class OrganizationEntityIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    private OrganizationEntity organization;

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Test org");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");
    }

    @DisplayName("This will save a basic organization")
    @Test
    void organizationCreation() {
        OrganizationEntity organizationEntity = entityManager.persistAndFlush(organization);
        assertNotNull(organizationEntity);
        assertEquals(organizationEntity.getName(), organization.getName());
        assertEquals(organizationEntity.getDescription(), organization.getDescription());
        assertEquals(organizationEntity.getOrgId(), organization.getOrgId());
    }


    @DisplayName("This will not able to save organization")
    @ParameterizedTest()
    @ValueSource(strings = {"test org, test_org, test&_donkey_org", "test &&y874y3"})
    void testOrganizationUniqueNameContrain(String name) {
        organization.setOrgId(name);
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(organization);
        });
    }
}