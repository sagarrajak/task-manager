package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.response.steps.CreateStepResponseDto;
import com.taskmanager.taskmanager.dto.response.steps.DeleteStepResponseDto;
import com.taskmanager.taskmanager.dto.response.steps.GetStepsResponseDto;
import com.taskmanager.taskmanager.dto.response.steps.UpdateStepResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/steps/")
public class StepsController {
    @PutMapping("{boardId}/{id}")
    public ResponseEntity<UpdateStepResponseDto> editSteps() {
        return null;
    }

    @DeleteMapping("{boardId}/{id}")
    public ResponseEntity<DeleteStepResponseDto> deleteSteps() {
        return null;
    }

    @GetMapping("{boardId}/{id}")
    public ResponseEntity<GetStepsResponseDto> getStepsOfABoard() {
        return null;
    }

    @PostMapping("{boardId}")
    public ResponseEntity<CreateStepResponseDto> createNewStep() {
        return null;
    }

    @PutMapping("order/{boardId}/")
    public ResponseEntity<UpdateStepResponseDto> updateOrder() {
        return null;
    }
}
