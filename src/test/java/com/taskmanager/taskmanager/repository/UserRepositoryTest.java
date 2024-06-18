package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository repositoryToTest;

    @Autowired
    TestEntityManager manager;

    UserEntity user;

    @BeforeEach
    void setup() {
        user = new UserEntity();
        user.setFirstName("sagar");
        user.setLastName("rajak");
        user.setEmail("saGaRrajak858@gmail.com");
        user.setPassword("sagar123");
        user = this.manager.persistAndFlush(user);
    }

    @Test
    void findByEmail() {
        Optional<UserEntity> oneByEmailIgnoreCase = repositoryToTest.findOneByEmailIgnoreCase("sagarrajak858@gmail.com");
        assertFalse(oneByEmailIgnoreCase.isEmpty());
        assertEquals(oneByEmailIgnoreCase.get().getEmail(), user.getEmail());
    }
}