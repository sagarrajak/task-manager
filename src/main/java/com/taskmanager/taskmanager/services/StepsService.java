package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.steps.CreateStepRequestDto;
import com.taskmanager.taskmanager.dto.request.steps.UpdateStepsDto;
import com.taskmanager.taskmanager.dto.request.steps.UpdateStepsOrderRequestDto;
import com.taskmanager.taskmanager.dto.response.steps.*;

public interface StepsService {
    UpdateStepResponseDto updateStep(UpdateStepsDto dto, Long stepId, Long boardId);

    CreateDefaultStepsResponseDto createDefaultSteps(Long boardId);

    DeleteStepResponseDto deleteStep(long boardId, Long stepId);

    ChangeOrderResponseDto changeOrder(Long boardId, UpdateStepsOrderRequestDto dto);

    GetStepsResponseDto getSteps(Long boardId);

    CreateStepResponseDto createNewStep(Long boardId, CreateStepRequestDto dto);
}
