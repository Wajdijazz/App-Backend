package com.followup.davidson.controllers;

import com.followup.davidson.Routes;
import com.followup.davidson.dto.PersonDto;
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

    @GetMapping("/")
    public List<ProjectDto> getAllProject() {
        return projectService.findAll();
    }

    @GetMapping("/active")
    public List<ProjectDto> getActiveProjects() {
        return projectService.findActiveProjects();
    }

    @PostMapping("/client/{clientId}/manager/{managerId}")
    public ProjectDto createProject(@Valid @RequestBody ProjectDto projectDto,
                                    @PathVariable(value = "clientId") Long clientId,
                                    @PathVariable(value = "managerId") Long managerId) {
        return projectService.createOrUpdate(projectDto, clientId, managerId);
    }

    @PutMapping("/client/{clientId}/manager/{managerId}")
    public ProjectDto updateProject(@Valid @RequestBody ProjectDto projectDto,
                                    @PathVariable(value = "clientId") Long clientId,
                                    @PathVariable(value = "managerId") Long managerId) {
        return projectService.createOrUpdate(projectDto, clientId, managerId);
    }

    @PutMapping("/{projectId}")
    public ProjectDto updateIsActiveByProjectId(@PathVariable(value = "projectId") Long projectId,
                                                @Valid @RequestBody ProjectDto projectDto) {
        return projectService.updateIsActiveByProjectId(projectId, projectDto.isActive());
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
