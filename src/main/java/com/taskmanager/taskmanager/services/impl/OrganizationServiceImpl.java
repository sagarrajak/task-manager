package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.dto.mapstruct.OrganizationMapper;
import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.request.StepsCrationRequest;
import com.taskmanager.taskmanager.dto.response.StepsCrationResponse;
import com.taskmanager.taskmanager.dto.response.TagCreationRequest;
import com.taskmanager.taskmanager.dto.response.TagCreationResponse;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.StepsEntity;
import com.taskmanager.taskmanager.entity.TagsEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.exception.OrganizationException;
import com.taskmanager.taskmanager.repository.OrganizationRepository;
import com.taskmanager.taskmanager.services.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

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
    public boolean addUserToOrganization(String email) {
        return false;
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
