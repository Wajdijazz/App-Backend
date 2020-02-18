package com.followup.davidson.converter;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Project;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IClientService;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements GenericsConverter<Project, ProjectDto> {
    private IClientService clientService;

    public ProjectConverter(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ProjectDto entityToDto(Project project) {
       return ProjectDto.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .build();
    }

    @Override
    public Project dtoToEntity(ProjectDto projectDto) {
        Client client=clientService.findById(projectDto.getClientId());
        return Project.builder()
                .projectId(projectDto.getProjectId())
                .projectName(projectDto.getProjectName())
                .client(client)
                .build();
    }
}
