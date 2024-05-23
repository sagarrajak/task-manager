package com.taskmanager.taskmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StepsEntityIntegrationTest {
    @Autowired
    TestEntityManager entityManager;

   StepsEntity steps;

   BoardEntity board;

   OrganizationEntity organization;

   @BeforeEach
   void setup() {
    organization = new OrganizationEntity();
    organization.setName("Test org");
    organization.setDescription("This is fake org");
    organization.setOrgId("testOrg");

    board = new BoardEntity();
    board.setName("Sagar Donkey Board");
    board.setDescription("THis is sample board");
    board.setBoardUniqueId("donkeyboard");

    steps = new StepsEntity();
    steps.setName("TODO");
    steps.setDescription("This is for task that are in pending state");
    steps.setColor("FF0000");
    steps.setOrder(1);
   }

    @Test
    @DisplayName("It will save default steps")
    void saveSteps() {
       var savedOrganization = entityManager.persistAndFlush(organization);
       board.setOrganization(savedOrganization);
       var savedBoard = entityManager.persistAndFlush(board);
       steps.setOrganization(savedOrganization);
       steps.setBoardId(savedBoard);
       var savedSteps = entityManager.persistAndFlush(steps);
       assertNotNull(savedSteps);
      System.out.println(savedSteps);
    }

}