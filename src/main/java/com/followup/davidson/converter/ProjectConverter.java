package com.followup.davidson.converter;

import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Project;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IClientService;
import com.followup.davidson.services.IManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class ProjectConverter implements GenericsConverter<Project, ProjectDto> {

    private ManagerConverter managerConverter;
    private ClientConverter clientConverter;


    @Override
    public ProjectDto entityToDto(Project project) {
        return ProjectDto.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .managerId(project.getManager().getManagerId())
                .clientId(project.getClient().getClientId())
                .managerDto(managerConverter.entityToDto(project.getManager()))
                .clientDto(clientConverter.entityToDto(project.getClient()))
                .build();
    }

    @Override
    public Project dtoToEntity(ProjectDto projectDto) {
        return Project.builder()
                .projectId(projectDto.getProjectId())
                .projectName(projectDto.getProjectName())
                .client(clientConverter.dtoToEntity(projectDto.getClientDto()))
                .manager(managerConverter.dtoToEntity(projectDto.getManagerDto()))
                .build();
    }
}
