package com.taskmanager.taskmanager.dto.response.organization;

import com.taskmanager.taskmanager.dto.response.users.UserResponse;
import com.taskmanager.taskmanager.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrganizationDetailsResponseDto {
    String name;
    String orgId;
    String description;
    List<UserResponse> users;
}
