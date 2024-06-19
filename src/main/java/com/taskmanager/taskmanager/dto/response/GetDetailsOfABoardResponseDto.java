package com.taskmanager.taskmanager.dto.response;

import com.taskmanager.taskmanager.dto.request.steps.StepsResponse;
import com.taskmanager.taskmanager.dto.response.board.CreateBoardResponseDto;
import com.taskmanager.taskmanager.dto.response.users.UserResponse;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailsOfABoardResponseDto extends CreateBoardResponseDto {
    List<UserResponse> users;
    List<StepsResponse> steps;
}
