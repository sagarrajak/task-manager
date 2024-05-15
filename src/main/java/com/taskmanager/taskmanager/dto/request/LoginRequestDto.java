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
public class LoginRequestDto {
    @NotBlank(message = "Email is required!")
    @Email(message = "Must be valid email!")
    String username;

    @NotBlank(message = "Password is required!")
    @Size(min=2, max = 100, message = "Password must be between 2 to 100 character long!")
    String password;
}
