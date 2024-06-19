package com.taskmanager.taskmanager.dto.response.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllBoardResponseDto {
    List<CreateBoardResponseDto> boards;
}
