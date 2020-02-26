package com.followup.davidson.services;

import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Project;

import java.util.Collection;
import java.util.List;

public interface IProjectService {

    List<ProjectDto> findAll();

    ProjectDto findById(Long id);

    ProjectDto createOrUpdate(ProjectDto projectDto);

    ProjectDto updateIsActiveByProjectId(Long projectId, Boolean isActive);

    ProjectDto findByProjectIdAndIsActiveTrue(Long projectId);

    void deleteProject(Long id);
}
