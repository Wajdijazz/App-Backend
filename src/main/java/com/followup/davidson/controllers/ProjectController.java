package com.followup.davidson.controllers;

import com.followup.davidson.Routes;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Project;
import com.followup.davidson.services.IProjectService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(Routes.PROJECT)

public class ProjectController {

    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/", produces = {"application/json"})
    public Collection<ProjectDto> getAllProject() {
        return projectService.findAll();
    }

    @PostMapping("/")
    public ProjectDto createProject(@Valid @RequestBody ProjectDto projectDto) {
        return projectService.createOrUpdate(projectDto);
    }

    @PutMapping("/")
    public ProjectDto updateProject(@Valid @RequestBody ProjectDto projectDto) {
        return projectService.createOrUpdate(projectDto);
    }

    @GetMapping("/{id}")
    public ProjectDto findProjectById(@PathVariable(value = "id") Long projectId) {
        return projectService.findById(projectId);
    }

    @DeleteMapping("/{id}")
    public void deletePeroject(@PathVariable(value = "id") Long projectId) {
        projectService.deleteProject(projectId);
    }


}
