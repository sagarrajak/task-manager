package com.taskmanager.taskmanager.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Used for testing Signup DTO")
class SignupRequestDtoTest {
    private static Validator validator;

    @BeforeAll()
    static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }


    @DisplayName("Should be a valid request")
    @Test
    void testAValidRequestWithAllValidation() {
        // arrange
        SignupRequestDto validRequest = SignupRequestDto.builder().email("sagarrajak88@gmail.com")
                .firstName("sagar")
                .lastName("rajak")
                .password("sagar")
                .build();
        // act
        Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(validRequest);

        // assert
        assertEquals(validate.size(), 0);
    }

    @DisplayName("Should be an invalid request!")
    @Test
    void testAnInValidDtoRequest() {
        SignupRequestDto invalidRequest = SignupRequestDto.builder().email("sagarrajak88")
                .firstName("s")
                .lastName("r")
                .password("s")
                .build();

        Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(invalidRequest);

        assertEquals(validate.size(), 4);
       for(var violation: validate) {
           switch (violation.getPropertyPath().toString()) {
               case "firstName":
                   assertEquals(violation.getMessage(), "First name must be 2 to 200 character long!");
                   break;
               case "lastName":
                   assertEquals(violation.getMessage(), "Last name must be 2 to 200 character long!");
                   break;
               case "email":
                   assertEquals(violation.getMessage(), "Must be valid email!");
                   break;
               case "password":
                   assertEquals(violation.getMessage(), "Password must be 2 to 100 character long!");
                   break;
                default:
                    break;
           }
       }
    }

    @DisplayName("Should check for an invalid empty request!")
    @Test
    void testAnEmptyDtoRequest() {
        SignupRequestDto invalidRequest = SignupRequestDto.builder()
                .build();

        Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(invalidRequest);

        assertEquals(validate.size(), 4);
        for(var violation: validate) {
            switch (violation.getPropertyPath().toString()) {
                case "firstName":
                    assertEquals(violation.getMessage(), "First name is required!");
                    break;
                case "lastName":
                    assertEquals(violation.getMessage(), "Last name is required!");
                    break;
                case "email":
                    assertEquals(violation.getMessage(), "Email is required!");
                    break;
                case "password":
                    assertEquals(violation.getMessage(), "Password is required!");
                    break;
                default:
                    break;
            }
        }
    }

}