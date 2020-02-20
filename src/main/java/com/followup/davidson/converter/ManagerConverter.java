package com.followup.davidson.converter;

import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.model.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerConverter implements GenericsConverter<Manager, ManagerDto> {
    @Override
    public ManagerDto entityToDto(Manager manager) {
        return ManagerDto.builder()
                .managerId(manager.getManagerId())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .build();
    }

    @Override
    public Manager dtoToEntity(ManagerDto managerDto) {
        return Manager.builder()
                .managerId(managerDto.getManagerId())
                .firstName(managerDto.getFirstName())
                .lastName(managerDto.getLastName())
                .build();
    }
}
