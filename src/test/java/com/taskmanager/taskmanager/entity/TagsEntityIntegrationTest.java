package com.taskmanager.taskmanager.entity;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagsEntityIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    private OrganizationEntity organization;


    BoardEntity board;
    StepsEntity steps;

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Testorg");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");
        organization = entityManager.persistAndFlush(organization);

        // setup board
        board = new BoardEntity();
        board.setName("Sagar Donkey Board");
        board.setDescription("THis is sample board");
        board.setBoardUniqueId("donkeyboard");
        board.setOrganization(organization);
        board = this.entityManager.persistAndFlush(board);

        // setup steps
        steps = new StepsEntity();
        steps.setName("TODO");
        steps.setDescription("This is for task that are in pending state");
        steps.setColor("FF0000");
        steps.setOrder(1);
        steps.setOrganization(organization);
        steps.setBoardId(board);
        steps = this.entityManager.persistAndFlush(steps);
    }

    @Test
    @DisplayName("Create a tag")
    void createTags() {
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setColor("FF0000");
        tagsEntity.setName("backend task");
        TagsEntity savedTag = entityManager.persistAndFlush(tagsEntity);
        Assertions.assertNotNull(savedTag);
        Assertions.assertNotNull(savedTag.getId());
        Assertions.assertEquals(savedTag.getColor(), tagsEntity.getColor());
        Assertions.assertEquals(savedTag.getName(), tagsEntity.getName());;
    }


    @Test
    @DisplayName("Create a tag and add to the task entity")
    void createTag_ifWeAddThisToTaskEntity_itWillBeVisibleOnTask() {
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setColor("FF0000");
        tagsEntity.setName("backend task");
        TagsEntity savedTag = entityManager.persistAndFlush(tagsEntity);

        TagsEntity tagsEntityBlue = new TagsEntity();
        tagsEntityBlue.setColor("FF0000");
        tagsEntityBlue.setName("Frontend Task");
        TagsEntity savedTagBlue = entityManager.persistAndFlush(tagsEntityBlue);

        // save task
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("Simple task");
        taskEntity.setDescription("This is task to piss pm");
        taskEntity.setSteps(steps);
        taskEntity.setBoard(board);
        taskEntity.setTags(new HashSet<>(Arrays.asList(savedTag, savedTagBlue)));
        TaskEntity savedTask = entityManager.persistAndFlush(taskEntity);

        // get

        Assertions.assertNotNull(savedTask.getTags());
        Assertions.assertEquals(savedTask.getTags().size(), 2);

    }
}