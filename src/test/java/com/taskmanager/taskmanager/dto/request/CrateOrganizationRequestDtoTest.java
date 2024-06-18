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

class CrateOrganizationRequestDtoTest {
    private static Validator validator;

    @BeforeAll()
    static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @DisplayName("Test for valid request!")
    @Test()
    void testCreateOrgDto() {
        CrateOrganizationRequestDto dto = new CrateOrganizationRequestDto();
        dto.setFullName("test org for plastic production");
        dto.setDescription("This is water polluting org");
        dto.setUniqueName("waterpollutingorg");
        Set<ConstraintViolation<CrateOrganizationRequestDto>> validationRequest = validator.validate(dto);
        assertEquals(validationRequest.size(), 0);
    }

    @DisplayName("Test for invalid request!")
    @Test()
    void testForInValidRequest() {
        CrateOrganizationRequestDto dto = new CrateOrganizationRequestDto();
        dto.setFullName("A plastic factory is a manufacturing facility where various plastic products are produced. Using processes like injection molding, extrusion, and blow molding, these factories convert raw plastic materials into items such as packaging, components, and consumer goods, ensuring precise quality control.");
        dto.setDescription("You might also consider using the open source SpringSandwich library which lets you" +
                " directly annotate in your Spring Boot controllers which interceptors to apply," +
                " much in the same way you annotate your url That way, no typo-prone Strings floating around -- SpringSandwich's " +
                "method and class annotations easily survive refactoring and make it clear what's being applied where." +
                " (Disclosure: I'm the author).You might also consider using the open source SpringSandwich library which lets" +
                " you directly annotate in your Spring Boot controllers which interceptors to apply, much in the same way you annotate your url" +
                " That way, no typo-prone Strings floating around -- SpringSandwich's method and class annotations easily survive refactoring and make" +
                " it clear what's being applied where. (Disclosure: I'm the author).You might also consider using the open source SpringSandwich library which lets" +
                " you directly annotate in your Spring Boot controllers which interceptors to apply, much in the same way you annotate your url That way," +
                " no typo-prone Strings floating around -- SpringSandwich's method and class annotations easily survive refactoring and make it" +
                " clear what's being applied where. (Disclosure: I'm the author).");
        dto.setUniqueName("waterpollutingorg232323asdsdfsdfsdfsdfsdfsdfsdfsdfsdfskjdfbskdjfbskdfjbskdfbjsdflksdflksdfn");
        Set<ConstraintViolation<CrateOrganizationRequestDto>> validationRequest = validator.validate(dto);
        for (var violations: validationRequest) {
            switch (violations.getPropertyPath().toString()) {
                case "fullName":
                    assertEquals("Name must be 2 to 200 character long!", violations.getMessage());
                    break;
                case "uniqueName":
                    assertEquals("Name must be 2 to 25 character long!", violations.getMessage());
                    break;
                case "description":
                    assertEquals("Description must be maximum 1000 character long!", violations.getMessage());
                    break;
                default:
                    break;
            }
        }
    }

    @DisplayName("Test for empty requst")
    @Test()
    void testForEmptyRequest() {
        CrateOrganizationRequestDto dto = new CrateOrganizationRequestDto();
        Set<ConstraintViolation<CrateOrganizationRequestDto>> validationRequest = validator.validate(dto);
        for (var violations: validationRequest) {
            switch (violations.getPropertyPath().toString()) {
                case "fullName":
                    assertEquals("Organization full name can't be empty!", violations.getMessage());
                    break;
                case "uniqueName":
                    assertEquals("Organization unique name can't be empty!", violations.getMessage());
                    break;
                default:
                    break;
            }
        }
    }
}