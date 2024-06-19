package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.annotation.RequireOrganizationMethod;
import com.taskmanager.taskmanager.dto.mapstruct.OrganizationMapper;
import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.request.organization.AddUserOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.GeneralResponseWithMessage;
import com.taskmanager.taskmanager.dto.response.organization.CreateOrganizationResponseDto;
import com.taskmanager.taskmanager.dto.response.organization.OrganizationDetailsResponseDto;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.services.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController()
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping("/{uniqueName}")
    public ResponseEntity<OrganizationDetailsResponseDto> getOrganizationDetails(@PathVariable String uniqueName) {
        OrganizationDetailsResponseDto detailsOfOrganization = this.organizationService.getAllDetailsOfOrganization(uniqueName);
        return new ResponseEntity<>(detailsOfOrganization, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateOrganizationResponseDto> createOrganization(@RequestBody @Valid CrateOrganizationRequestDto requestDto) {
        OrganizationEntity organization = this.organizationService.createOrganization(requestDto);
        this.organizationService.addSelfToOrganizationAsAdmin(organization);
        CreateOrganizationResponseDto createOrganizationResponseDto = OrganizationMapper.INSTANCE.organizationToCreateOrganizationResponseDto(organization);
        return new ResponseEntity<>(createOrganizationResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/add-user")
    @RequireOrganizationMethod()
    public ResponseEntity<GeneralResponseWithMessage<OrganizationDetailsResponseDto>> addUserOrganization(@RequestBody @Valid AddUserOrganizationRequestDto dto) {
        OrganizationDetailsResponseDto organizationDetailsResponseDto = this.organizationService.addUserToOrganization(dto.getEmail());
        return new ResponseEntity<>(GeneralResponseWithMessage.of(organizationDetailsResponseDto, "User added successfully"), HttpStatus.OK);
    }

}
