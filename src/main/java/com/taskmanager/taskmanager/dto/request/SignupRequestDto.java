package com.taskmanager.taskmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {
    @NotBlank()
    String firstName;
    @NotBlank()
    String lastName;
    @NotBlank()
    @Email()
    String email;
    @NotBlank()
    String password;
}

