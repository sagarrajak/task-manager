package com.taskmanager.taskmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskEntityIntegrationTest {

    @Autowired
    TestEntityManager testEntityManager;

    BoardEntity board;
    StepsEntity steps;

    OrganizationEntity organization;


    @BeforeEach()
    void setup() {
        organization = new OrganizationEntity();
        board = new BoardEntity();
        steps = new StepsEntity();

        // setup organization
        organization.setName("testorg");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");

        organization = this.testEntityManager.persistAndFlush(organization);

        // setup board
        board.setName("Sagar Donkey Board");
        board.setDescription("THis is sample board");
        board.setBoardUniqueId("donkeyboard");
        board.setOrganization(organization);
        board = this.testEntityManager.persistAndFlush(board);

        // setup steps
        steps.setName("TODO");
        steps.setDescription("This is for task that are in pending state");
        steps.setColor("FF0000");
        steps.setOrder(1);
        steps.setOrganization(organization);
        steps.setBoardId(board);
        steps = this.testEntityManager.persistAndFlush(steps);
    }

    @Test
    @DisplayName("Create a sample task with minimum values")
    void createSampleTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("Simple task");
        taskEntity.setDescription("This is task to piss pm");
        taskEntity.setSteps(steps);
        taskEntity.setBoard(board);
        TaskEntity savedTask = testEntityManager.persistAndFlush(taskEntity);
        assertNotNull(savedTask);
        assertNotNull(savedTask.getBoard());
        assertNotNull(savedTask.getBoard());
    }


    @Test
    @DisplayName("Create a task with all values")
    void createSampleTask_ifWeProvideAllValues_ItShouldCreateATask() {

    }

    @Test
    @DisplayName("Create a task with comment")
    void createASampleTask_ifWeAddComment_itMustBeVisibleToTask() {

    }

    @Test
    @DisplayName("Create a task with history")
    void createATask_ifWeChangeAValuesInTask_ItWillBeVisibleInTaskHistory() {


    }
}