package com.followup.davidson.controllers;

import com.followup.davidson.Routes;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.IProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Routes.PROJECT)

public class ProjectController {

    private IProjectService projectService;

    private ClientRepository clientRepository;

    private ProjectRepository projectRepository;

    public ProjectController(IProjectService projectService, ClientRepository clientRepository,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.clientRepository = clientRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping(value = "/", produces = {"application/json"})
    public List<Project> getAllProject() {
        return projectService.findAll();
    }


    @PostMapping("/client/{clientId}/project")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project, @PathVariable(value = "clientId") Long clientId) {
        Project projectAdded = projectService.create(project, clientId);
        if (projectAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projectId}")
                .buildAndExpand(project.getProjectId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{projectId}/{clientId}")
    public Project updateProject(@PathVariable(value = "projectId") Long projectId, @Valid @RequestBody Project project,
                                 @PathVariable(value = "clientId") Long clientId) {
        return projectService.updateProject(projectId, project, clientId);
    }

    @GetMapping("/{id}")
    public Optional<Project> findProjectById(@PathVariable(value = "id") Long projectId) {
        return projectService.findById(projectId);
    }

    @DeleteMapping("/{id}")
    public void deletePeroject(@PathVariable(value = "id") Long projectId) {
        projectService.deleteProject(projectId);
    }


}
