package com.taskmanager.taskmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrateOrganizationRequestDto {

    @NotBlank(message = "Organization full name can't be empty!")
    @Size(min = 2, max = 200, message = "Name must be 2 to 200 character long!")
    String fullName;

    @NotBlank(message = "Organization unique name can't be empty!")
    @Size(min = 2, max = 25, message = "Name must be 2 to 25 character long!")
    String uniqueName;

    @Size(max = 1000, message = "Description must be maximum 1000 character long!")
    String description;
}
