package com.taskmanager.taskmanager.dto.response.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBoardResponseDto {
    String uniqueName;
    String name;
    String description;
    Long id;
}
