package com.taskmanager.taskmanager.dto.response.organization;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrganizationResponseDto {
    String name;
    String description;
    String uniqueName;
}
