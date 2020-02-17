package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.ITJService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(Routes.TJ)
public class TJController {


    private ITJService tjService;
    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    public TJController(ITJService tjService, ProjectRepository projectRepository, PersonRepository personRepository) {
        this.tjService = tjService;
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public List<TJ> getAllTj() {
        return tjService.findAll();
    }

    @PostMapping("/project/{projectId}/person/{personId}")
    public TJ createTj(@Valid @RequestBody TJ tj, @PathVariable(value = "projectId") Long projectId,
                                       @PathVariable(value = "personId") Long personId) {
     return    tjService.create(tj, projectId, personId);
    }

    @PutMapping("/{tjId}/{projectId}/{personId}")
    public TJ updateTj(@Valid @RequestBody TJ tj, @PathVariable(value = "tjId") Long tjId, @PathVariable(value = "projectId") Long projectId,
                                       @PathVariable(value = "personId") Long personId) {
      return tjService.updateTj(tjId, tj, projectId, personId);
    }

    @GetMapping("/{id}")
    public TJ findTjById(@PathVariable(value = "id") Long tjId) {
        return tjService.findById(tjId);
    }

    @GetMapping("/{projectId}/{personId}")
    public Long findTarif(@PathVariable(value = "projectId") Long projectId,
                          @PathVariable(value = "personId") Long personId) {
        return tjService.findTarif(projectId, personId);
    }

    @DeleteMapping("/{id}")
    public void deleteTj(@PathVariable(value = "id") Long tjId) {
        tjService.deleteTj(tjId);
    }

}
