package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.board.CreateBoardRequestDto;
import com.taskmanager.taskmanager.dto.request.board.UpdateBoardRequestDto;
import com.taskmanager.taskmanager.dto.response.GetDetailsOfABoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.CreateBoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.GetAllBoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.UpdateBoardResponseDto;

public interface BoardService {
    CreateBoardResponseDto createNewBoard(CreateBoardRequestDto dto);
    UpdateBoardResponseDto updateBoardDetails(UpdateBoardRequestDto dto);
    GetAllBoardResponseDto getAllBoardsOfLoginUser();
    GetDetailsOfABoardResponseDto getDetailsOfABoard(String boardId);
}
