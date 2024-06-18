package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.mapstruct.OrganizationMapper;
import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.organization.AddUserOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.organization.CreateOrganizationResponseDto;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping()
    public CreateOrganizationResponseDto createOrganization(CrateOrganizationRequestDto requestDto) {
        OrganizationEntity organization = this.organizationService.createOrganization(requestDto);
        this.organizationService.addSelfToOrganizationAsAdmin(organization);
        return OrganizationMapper.INSTANCE.organizationToCreateOrganizationResponseDto(organization);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Boolean> addUserOrganization(AddUserOrganizationRequestDto dto) {
        return null;
    }
}
