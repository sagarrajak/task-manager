package com.taskmanager.taskmanager.dto.mapstruct;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.dto.response.users.UserResponse;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserResponse userToUserResponse(UserEntity dto);
}
