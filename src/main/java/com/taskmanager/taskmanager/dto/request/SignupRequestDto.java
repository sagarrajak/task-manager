package com.taskmanager.taskmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {
    @NotBlank(message = "First name is required!")
    @Size(min = 2, max = 200, message = "First name must be 2 to 200 character long!")
    String firstName;

    @NotBlank(message = "Last name is required!")
    @Size(min = 2, max = 200, message = "Last name must be 2 to 200 character long!")
    String lastName;

    @NotBlank(message = "Email is required!")
    @Email(message = "Must be valid email!")
    String email;


    @NotBlank(message = "Password is required!")
    @Size(min = 2, max = 100, message = "Password must be 2 to 100 character long!")
    String password;
}

