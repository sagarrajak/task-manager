package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.organization.OrganizationDetailsResponseDto;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface OrganizationService {
    public OrganizationEntity createOrganization(CrateOrganizationRequestDto requestDto);

    public Optional<OrganizationEntity> checkIfOrganizationExist(String nameOfOrg);

    public boolean addUserToOrganization(String email);

    @Transactional()
    boolean addUserToOrganization(String email, OrganizationEntity organization);

    @Transactional()
    boolean addUserToOrganization(UserEntity user, OrganizationEntity organization);

    public boolean removeUserFromOrganization(OrganizationEntity organization);

    public boolean checkIfUserHaveAccessToThisOrganization(UserEntity currentUser, OrganizationEntity currentOrganization);

    public boolean addSelfToOrganizationAsAdmin(OrganizationEntity organization);

    public OrganizationDetailsResponseDto getAllDetailsOfOrganization(String organizationId);
}
