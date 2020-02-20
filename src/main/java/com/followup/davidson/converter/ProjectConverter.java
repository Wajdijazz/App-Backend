package com.followup.davidson.converter;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Project;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IClientService;
import com.followup.davidson.services.IManagerService;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements GenericsConverter<Project, ProjectDto> {
    private IClientService clientService;
    private IManagerService managerService;

    public ProjectConverter(IClientService clientService,IManagerService managerService) {
        this.clientService = clientService;
        this.managerService=managerService;
    }

    @Override
    public ProjectDto entityToDto(Project project) {
        return ProjectDto.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .manager(project.getManager())
                .client(project.getClient())
                .build();
    }

    @Override
    public Project dtoToEntity(ProjectDto projectDto) {
        Client client = clientService.findById(projectDto.getClientId());
        Manager manager = managerService.findById(projectDto.getManagerId());
        return Project.builder()
                .projectId(projectDto.getProjectId())
                .projectName(projectDto.getProjectName())
                .client(client)
                .manager(manager)
                .build();
    }
}
