package com.taskmanager.taskmanager.dto.mapstruct;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper( OrganizationMapper.class );

    @Mapping(source = "description", target = "description")
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "uniqueName", target = "orgId")
    OrganizationEntity crateOrganizationRequestDtoToOrganization(CrateOrganizationRequestDto dto);
}
