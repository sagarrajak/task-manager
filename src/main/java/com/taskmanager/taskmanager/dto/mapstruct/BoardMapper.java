package com.taskmanager.taskmanager.dto.mapstruct;

import com.taskmanager.taskmanager.dto.request.board.CreateBoardRequestDto;
import com.taskmanager.taskmanager.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BoardMapper {
    @Mapping(source ="uniqueName", target = "boardUniqueId")
    BoardEntity createBoardRequestDtoToBoard(CreateBoardRequestDto dto);

    @Mapping(source = "boardUniqueId", target = "uniqueName")
    CreateBoardRequestDto boardEntityToCreateBoardResponseDto(BoardEntity board);
}
