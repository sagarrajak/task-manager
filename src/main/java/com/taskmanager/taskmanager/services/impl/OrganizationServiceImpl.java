package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.request.StepsCrationRequest;
import com.taskmanager.taskmanager.dto.response.StepsCrationResponse;
import com.taskmanager.taskmanager.dto.response.TagCreationRequest;
import com.taskmanager.taskmanager.dto.response.TagCreationResponse;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.StepsEntity;
import com.taskmanager.taskmanager.entity.TagsEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.services.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public void createOrganization(CrateOrganizationRequestDto requestDto) {
    }

    @Override
    public Optional<OrganizationEntity> checkIfOrganizationExist(String nameOfOrg) {
        return Optional.empty();
    }

    @Override
    public boolean addUserToOrganization(String email) {
        return false;
    }

    @Override
    public boolean removeUserFromOrganization(OrganizationEntity organization) {
        return false;
    }

    @Override
    public boolean checkIfUserHaveAccessToThisOrganization(UserEntity currentUser, OrganizationEntity currentOrganization) {
        return false;
    }

    @Override
    public TagCreationResponse addExistingTagToTheOrganization(TagCreationRequest tagCreationRequest) {
        return null;
    }

    @Override
    public boolean deleteATagFromTheOrganization(TagsEntity tag) {
        return false;
    }

    @Override
    public StepsCrationResponse addStepsInTheOrganization(List<StepsCrationRequest> stepsCreationRequests) {
        return null;
    }

    @Override
    public StepsCrationResponse addStepsInTheOrganization(StepsCrationRequest stepsCreationRequests) {
        return null;
    }

    @Override
    public boolean removeAStepFromOrganization(StepsEntity step) {
        return false;
    }
}
