package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.board.CreateBoardRequestDto;
import com.taskmanager.taskmanager.dto.request.board.UpdateBoardRequestDto;
import com.taskmanager.taskmanager.dto.response.GetDetailsOfABoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.CreateBoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.GetAllBoardResponseDto;
import com.taskmanager.taskmanager.dto.response.board.UpdateBoardResponseDto;
import com.taskmanager.taskmanager.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public CreateBoardResponseDto createNewBoard(CreateBoardRequestDto dto) {
        return null;
    }

    @Override
    public UpdateBoardResponseDto updateBoardDetails(UpdateBoardRequestDto dto) {
        return null;
    }

    @Override
    public GetAllBoardResponseDto getAllBoardsOfLoginUser() {
        return null;
    }

    @Override
    public GetDetailsOfABoardResponseDto getDetailsOfABoard(String boardId) {
        return null;
    }
}
