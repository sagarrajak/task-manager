package com.taskmanager.taskmanager.dto.response;

import com.taskmanager.taskmanager.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse {
    String message;
    UserEntity user;
}
