package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.dto.request.steps.CreateStepRequestDto;
import com.taskmanager.taskmanager.dto.request.steps.UpdateStepsDto;
import com.taskmanager.taskmanager.dto.request.steps.UpdateStepsOrderRequestDto;
import com.taskmanager.taskmanager.dto.response.steps.*;
import com.taskmanager.taskmanager.services.StepsService;
import org.springframework.stereotype.Service;

@Service
public class StepsServiceImpl implements StepsService {

    @Override
    public UpdateStepResponseDto updateStep(UpdateStepsDto dto, Long stepId, Long boardId) {
        return null;
    }

    @Override
    public CreateDefaultStepsResponseDto createDefaultSteps(Long boardId) {
        return null;
    }

    @Override
    public DeleteStepResponseDto deleteStep(long boardId, Long stepId) {
        return null;
    }

    @Override
    public ChangeOrderResponseDto changeOrder(Long boardId, UpdateStepsOrderRequestDto dto) {
        return null;
    }

    @Override
    public GetStepsResponseDto getSteps(Long boardId) {
        return null;
    }

    @Override
    public CreateStepResponseDto createNewStep(Long boardId, CreateStepRequestDto dto) {
        return null;
    }
}
