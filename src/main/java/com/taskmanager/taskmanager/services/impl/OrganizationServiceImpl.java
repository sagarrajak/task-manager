package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.annotation.RequireOrganizationMethod;
import com.taskmanager.taskmanager.dto.mapstruct.OrganizationMapper;
import com.taskmanager.taskmanager.dto.mapstruct.UserMapper;
import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.organization.OrganizationDetailsResponseDto;
import com.taskmanager.taskmanager.dto.response.users.UserResponse;
import com.taskmanager.taskmanager.entity.*;
import com.taskmanager.taskmanager.exception.OrganizationException;
import com.taskmanager.taskmanager.repository.OrganizationRepository;
import com.taskmanager.taskmanager.repository.UserOrganizationTableRepository;
import com.taskmanager.taskmanager.repository.UserRepository;
import com.taskmanager.taskmanager.services.OrganizationService;
import com.taskmanager.taskmanager.utill.RequestContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserOrganizationTableRepository userOrganizationTableRepository;
    private final RequestContextHolder contextHolder;
    private final AuthenticationService authenticationService;
    private final EntityManager entityManager;

    private final UserRepository userRepository;

    public OrganizationServiceImpl(
            OrganizationRepository organizationRepository,
            UserOrganizationTableRepository userOrganizationTableRepository,
            RequestContextHolder contextHolder,
            @Lazy AuthenticationService authenticationService,
            EntityManager entityManager, UserRepository userRepository
    ) {
        this.organizationRepository = organizationRepository;
        this.userOrganizationTableRepository = userOrganizationTableRepository;
        this.contextHolder = contextHolder;
        this.authenticationService = authenticationService;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrganizationEntity createOrganization(CrateOrganizationRequestDto requestDto) {
        if (this.checkIfOrganizationExist(requestDto.getUniqueName()).isEmpty()) {
            OrganizationEntity organizationEntity = OrganizationMapper.INSTANCE.crateOrganizationRequestDtoToOrganization(requestDto);
            OrganizationEntity savedOrganization = organizationRepository.save(organizationEntity);
            this.addSelfToOrganizationAsAdmin(organizationEntity);
            return savedOrganization;
        }
        throw new OrganizationException("Organization with same unique name exist, try another name!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<OrganizationEntity> checkIfOrganizationExist(String nameOfOrg) {
        return this.organizationRepository.findOneByOrgIdIgnoreCase(nameOfOrg.toLowerCase());
    }

    @Override
    @Transactional
    public OrganizationDetailsResponseDto addUserToOrganization(String email) {
        OrganizationEntity organization = (OrganizationEntity) contextHolder.get("organization");
        boolean isUserAlreadyExist = this.userOrganizationTableRepository.checkIfUserAlreadyExistInOrg(
                organization.getOrgId(), email);
        Optional<UserEntity> userToAdd = this.userRepository.findOneByEmailIgnoreCase(email);
        userToAdd.orElseThrow(() -> new OrganizationException("No user found with given email", HttpStatus.NOT_FOUND));
        if (!isUserAlreadyExist) {
            this.addUserToOrganization(userToAdd.get(), organization);
            return this.getAllDetailsOfOrganization(organization);
        }
        throw new OrganizationException("User already added in organization!", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional()
    public boolean addUserToOrganization(UserEntity user, OrganizationEntity organization) {
        UserOrganizationTable userOrganizationTable = new UserOrganizationTable();
        userOrganizationTable.setOrganization(entityManager.merge(organization));
        userOrganizationTable.setUser(entityManager.merge(user));
        this.userOrganizationTableRepository.save(userOrganizationTable);
        return true;
    }

    @Override
    @Transactional()
    public boolean addUserToOrganization(String email, OrganizationEntity organization) {
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
    public boolean addSelfToOrganizationAsAdmin(OrganizationEntity organization) {
        UserEntity currentLoginUser = this.authenticationService.getCurrentLoginUser();
        return addUserToOrganization(currentLoginUser, organization);
    }

    @Override
    public OrganizationDetailsResponseDto getAllDetailsOfOrganization(String organizationId) {
        if (!this.organizationRepository.isThereOrganizationWithSameName(organizationId))
            throw new OrganizationException("Organization not found!", HttpStatus.NOT_FOUND);
        OrganizationEntity organization =
                this.organizationRepository.findOneByOrgIdIgnoreCase(organizationId).get();
        List<UserEntity> userOfGivenOrg = this.userOrganizationTableRepository.findUserOfGivenOrg(organizationId);
        List<UserResponse> userResponsesDto = userOfGivenOrg.stream().map(UserMapper.INSTANCE::userToUserResponse).collect(Collectors.toList());
        return OrganizationDetailsResponseDto
                .builder()
                .orgId(organization.getOrgId())
                .users(userResponsesDto)
                .description(organization.getDescription())
                .name(organization.getName())
                .build();
    }

    @Override
    public OrganizationDetailsResponseDto getAllDetailsOfOrganization(OrganizationEntity organization) {
        List<UserEntity> userOfGivenOrg = this.userOrganizationTableRepository.findUserOfGivenOrg(organization.getOrgId());
        List<UserResponse> userResponsesDto = userOfGivenOrg.stream().map(UserMapper.INSTANCE::userToUserResponse).collect(Collectors.toList());
        return OrganizationDetailsResponseDto
                .builder()
                .orgId(organization.getOrgId())
                .users(userResponsesDto)
                .description(organization.getDescription())
                .name(organization.getName())
                .build();
    }
}
