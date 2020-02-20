package com.followup.davidson.services;

import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Project;

import java.util.List;

public interface IProjectService {

    List<Project> findAll();

    Project findById(Long id);

    ProjectDto createOrUpdate(ProjectDto projectDto);

    void deleteProject(Long id);
}
