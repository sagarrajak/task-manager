package com.taskmanager.taskmanager.dto.request.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardRequestDto {
    @NotBlank(message = "Unique name can't be blank")
    @Size(min = 1, max = 25, message = "Unique name must be below 25")
    @Pattern(regexp = "^\\S*$", message = "space not allowed")
    String uniqueName;

    @NotBlank(message = "Name can't be blank")
    String name;

    @NotBlank(message = "Description can't be blank")
    String description;
}
