package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.request.StepsCrationRequest;
import com.taskmanager.taskmanager.dto.response.StepsCrationResponse;
import com.taskmanager.taskmanager.dto.response.TagCreationRequest;
import com.taskmanager.taskmanager.dto.response.TagCreationResponse;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.StepsEntity;
import com.taskmanager.taskmanager.entity.TagsEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    public OrganizationEntity createOrganization(CrateOrganizationRequestDto requestDto);

    public Optional<OrganizationEntity> checkIfOrganizationExist(String nameOfOrg);

    public boolean addUserToOrganization(String email);

    @Transactional()
    boolean addUserToOrganization(String email, OrganizationEntity organization);

    public boolean removeUserFromOrganization(OrganizationEntity organization);

    public boolean checkIfUserHaveAccessToThisOrganization(UserEntity currentUser, OrganizationEntity currentOrganization);

    public TagCreationResponse addExistingTagToTheOrganization(TagCreationRequest tagCreationRequest);

    public boolean deleteATagFromTheOrganization(TagsEntity tag);

    public StepsCrationResponse addStepsInTheOrganization(List<StepsCrationRequest> stepsCreationRequests);

    public StepsCrationResponse addStepsInTheOrganization(StepsCrationRequest stepsCreationRequests);

    public boolean removeAStepFromOrganization(StepsEntity step);
}
