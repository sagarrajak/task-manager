package com.taskmanager.taskmanager.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardEntityIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    private OrganizationEntity organization;
    private BoardEntity board;

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
    }

    @Test
    @DisplayName("Create a simple board with no added organization")
    void testCreateBoard() {
        // act
        OrganizationEntity savedOrganization = entityManager.persistAndFlush(organization);
        board.setOrganization(savedOrganization);
        BoardEntity boardEntity = entityManager.persistAndFlush(board);

        // assert
        assertNotNull(boardEntity);
        assertEquals(boardEntity.getName(), board.getName());
        assertEquals(boardEntity.getBoardUniqueId(), board.getBoardUniqueId());
        assertEquals(boardEntity.getDescription(), board.getDescription());
        assertTrue(boardEntity.getUpdated_at().isBefore(LocalDateTime.now()));
        assertNotNull(boardEntity.getOrganization());
        System.out.println(boardEntity);
    }

    @Test
    @DisplayName("Throw error when unique id contains space")
    void testCreateBoard_whenWhitSpaceInBoardName_itsFailToCreateBoard() {
        board.setBoardUniqueId("sagar board");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            OrganizationEntity savedOrganization = entityManager.persistAndFlush(organization);
            board.setOrganization(savedOrganization);
            entityManager.persistAndFlush(board);
        });
    }

    @Test
    @DisplayName("Throw error when adding board without organization")
    void testCreateBoard_whenNoOrganizationProvidedItsThrowError() {
        Assertions.assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(board);
        });
    }

}