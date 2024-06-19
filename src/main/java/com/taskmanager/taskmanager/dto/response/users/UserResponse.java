package com.taskmanager.taskmanager.dto.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {
    String firstName;
    String lastName;
    Long id;
    String email;
    boolean disabled;
}
