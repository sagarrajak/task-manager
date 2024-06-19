package com.taskmanager.taskmanager.dto.request.organization;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddUserOrganizationRequestDto {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "invalid email  address")
    private String email;
}
