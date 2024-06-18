package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.annotation.RequireOrganizationMethod;
import com.taskmanager.taskmanager.dto.mapstruct.OrganizationMapper;
import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.request.StepsCrationRequest;
import com.taskmanager.taskmanager.dto.response.StepsCrationResponse;
import com.taskmanager.taskmanager.dto.response.TagCreationRequest;
import com.taskmanager.taskmanager.dto.response.TagCreationResponse;
import com.taskmanager.taskmanager.entity.*;
import com.taskmanager.taskmanager.exception.OrganizationException;
import com.taskmanager.taskmanager.repository.OrganizationRepository;
import com.taskmanager.taskmanager.repository.UserOrganizationTableRepository;
import com.taskmanager.taskmanager.services.OrganizationService;
import com.taskmanager.taskmanager.utill.RequestContextHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserOrganizationTableRepository userOrganizationTableRepository;
    private final RequestContextHolder contextHolder;
    private final AuthenticationService authenticationService;

    public OrganizationServiceImpl(
            OrganizationRepository organizationRepository,
            UserOrganizationTableRepository userOrganizationTableRepository,
            RequestContextHolder contextHolder,
            @Lazy AuthenticationService authenticationService
    ) {
        this.organizationRepository = organizationRepository;
        this.userOrganizationTableRepository = userOrganizationTableRepository;
        this.contextHolder = contextHolder;
        this.authenticationService = authenticationService;
    }

    @Override
    @Transactional()
    public OrganizationEntity createOrganization(CrateOrganizationRequestDto requestDto) {
        if (this.checkIfOrganizationExist(requestDto.getUniqueName()).isEmpty()) {
            OrganizationEntity organizationEntity = OrganizationMapper.INSTANCE.crateOrganizationRequestDtoToOrganization(requestDto);
            try {
                return organizationRepository.save(organizationEntity);
            } catch (Exception e) {
                throw new OrganizationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        throw new OrganizationException("Organization with same unique name exist, try another name!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<OrganizationEntity> checkIfOrganizationExist(String nameOfOrg) {
        return this.organizationRepository.findOneByOrgIdIgnoreCase(nameOfOrg.toLowerCase());
    }

    @Override
    @Transactional
    @RequireOrganizationMethod()
    public boolean addUserToOrganization(String email) {
        OrganizationEntity organization = (OrganizationEntity) contextHolder.get("organization");
        boolean isUserAlreadyExist = this.userOrganizationTableRepository.checkIfUserAlreadyExistInOrg(
                organization.getOrgId(), authenticationService.getCurrentLoginUser().getEmail());
        if (!isUserAlreadyExist) {
           return this.addUserToOrganization(authenticationService.getCurrentLoginUser(), organization);
        }
        throw new OrganizationException("User already added in organization!", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional()
    public boolean addUserToOrganization(String email, OrganizationEntity organization) {
        return false;
    }

    @Override
    @Transactional()
    public boolean addUserToOrganization(UserEntity user, OrganizationEntity organization) {
        UserOrganizationTable userOrganizationTable = new UserOrganizationTable();
        userOrganizationTable.setOrganization(organization);
        userOrganizationTable.setUser(user);
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
        return false;
    }
}
