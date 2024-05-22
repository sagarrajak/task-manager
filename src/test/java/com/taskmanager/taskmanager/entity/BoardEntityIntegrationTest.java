package com.taskmanager.taskmanager.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    @DisplayName("Create a simple board with no added organization")
    void testCreateBoard() {
        BoardEntity board = new BoardEntity();
        board.setName("Sagar Donkey Board");
        board.setDescription("THis is sample board");
        board.setBoardUniqueId("donkeyboard");

        BoardEntity boardEntity = entityManager.persistAndFlush(board);
        assertNotNull(boardEntity);
        assertEquals(boardEntity.getName(), board.getName());
        assertEquals(boardEntity.getBoardUniqueId(), board.getBoardUniqueId());
        assertEquals(boardEntity.getDescription(), board.getDescription());
        assertTrue(boardEntity.getUpdated_at().isBefore(LocalDateTime.now()));
        System.out.println(boardEntity);
    }

    @Test
    @DisplayName("Throw error when unique id contains space")
    void testCreateBoard_whenWhitSpaceInBoardName_itsFailToCreateBoard() {
        BoardEntity board = new BoardEntity();
        board.setBoardUniqueId("sagar board");
        board.setName("Sagar Donkey Board");
        board.setDescription("THis is sample board");
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(board);
        });
    }
}