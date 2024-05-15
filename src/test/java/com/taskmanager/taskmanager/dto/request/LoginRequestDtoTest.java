package com.taskmanager.taskmanager.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDtoTest {
    private static Validator validator;

    @BeforeAll()
    static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("Test for valid request")
    void testValidRequest() {
        var validLoginRequest = LoginRequestDto.builder().
                username("sagarrajak@gmail.com")
                .password("sagar").build();

        var validations = validator.validate(validLoginRequest);
        assertEquals(validations.size(), 0);
    }

    @Test
    @DisplayName("Test for invalid request")
    void testInvalidRequest() {
        var inValidLoginRequest = LoginRequestDto.builder().
                username("sagarrajak")
                .password("s")
                .build();
        Set<ConstraintViolation<LoginRequestDto>> validate = validator.validate(inValidLoginRequest);
        for (var violations: validate) {
            switch (violations.getPropertyPath().toString()) {
                case "email":
                    assertEquals("Must be valid email!", violations.getMessage());
                    break;
                case "password":
                    assertEquals("Password must be between 2 to 100 character long!", violations.getMessage());
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    @DisplayName("Test for empty request")
    void testEmptyRequest() {
        var inValidLoginRequest = LoginRequestDto.builder()
                .build();
        Set<ConstraintViolation<LoginRequestDto>> validate = validator.validate(inValidLoginRequest);
        for (var violations: validate) {
            switch (violations.getPropertyPath().toString()) {
                case "email":
                    assertEquals("Email is required!", violations.getMessage());
                    break;
                case "password":
                    assertEquals("Password is required!", violations.getMessage());
                    break;
                default:
                    break;
            }
        }
    }

}