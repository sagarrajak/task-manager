package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.BoardEntity;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BoardRepository boardRepository;

    private OrganizationEntity organization;
    private BoardEntity board;

    @BeforeEach
    void setup() {
        organization = new OrganizationEntity();
        organization.setName("Test org");
        organization.setDescription("This is fake org");
        organization.setOrgId("testOrg");
        organization = entityManager.persistAndFlush(organization);
        board = new BoardEntity();
        board.setName("Sagar Donkey Board");
        board.setOrganization(organization);
        board.setDescription("THis is sample board");
        board.setBoardUniqueId("donH23423");
        board = entityManager.persistAndFlush(board);
    }


    @Test()
    @DisplayName("Check method is isThereBoardWithSameName")
    void testMethodIsThereBoardWithSameName_ifSavedBoardFound_itWillReturnTrue() {
        boolean boardFound = boardRepository.isThereBoardWithSameName("DONH23423");
        Assertions.assertEquals(boardFound, true);
    }
}