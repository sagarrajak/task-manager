package com.taskmanager.taskmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentsEntityIntegrationTest {

    @Autowired
    TestEntityManager testEntityManager;

    BoardEntity board;
    StepsEntity steps;

    OrganizationEntity organization;

    TaskEntity taskEntity;


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

        // setup task
        taskEntity = new TaskEntity();
        taskEntity.setName("Simple task");
        taskEntity.setDescription("This is task to piss pm");
        taskEntity.setSteps(steps);
        taskEntity.setBoard(board);
        taskEntity = testEntityManager.persistAndFlush(taskEntity);
    }


    @Test
    @DisplayName("Test a comment in task")
    void createSampleComment() {
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setComment("this is sample comment");
        commentsEntity.setTask(taskEntity);
        var savedComment = testEntityManager.persistAndFlush(commentsEntity);
        assertNotNull(savedComment);
        assertEquals(savedComment.getComment(), commentsEntity.getComment());
        assertEquals(savedComment.getTask().getId(), taskEntity.getId());
        System.out.println(savedComment);
    }

}