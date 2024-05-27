package com.taskmanager.taskmanager.dto.mapstruct;

import com.taskmanager.taskmanager.dto.request.CrateOrganizationRequestDto;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class OrganizationMapperTest {
    @Test
    void testMappingFromCreateOrganizationToOrganizationEntity() {
        CrateOrganizationRequestDto dto = new CrateOrganizationRequestDto();
        dto.setDescription("LKnsfsdnfs ksjdfhf  sdfihsdfjk fdkjsdnf");
        dto.setUniqueName("Testorg");
        dto.setFullName("This is sime org");
        OrganizationEntity organizationEntity = OrganizationMapper.INSTANCE.crateOrganizationRequestDtoToOrganization(dto);
        Assertions.assertNotNull(organizationEntity);
        Assertions.assertEquals(organizationEntity.getOrgId(), dto.getUniqueName());
        Assertions.assertEquals(organizationEntity.getName(), dto.getFullName());
        Assertions.assertEquals(organizationEntity.getDescription(), dto.getDescription());
    }
}