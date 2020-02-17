package com.followup.davidson.converter;

import com.followup.davidson.model.Project;
import com.followup.davidson.dto.ProjectDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements GenericsConverter<Project, ProjectDto> {

    @Override
    public ProjectDto entityToDto(Project project) {
        ProjectDto projectDto = ProjectDto.builder()
                .projectId(project.getProjectId())
                .project(project.getProjectName())
                .build();
        return projectDto;
    }

    @Override
    public Project dtoToEntity(ProjectDto projectDto) {
        return null;
    }
}
