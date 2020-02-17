package com.followup.davidson.controllers;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.followup.davidson.Routes;
import com.followup.davidson.model.Intervention;

import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.IInterventionService;

import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.InterventionRepository;

import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
import lombok.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Routes.INTERVENTION)
public class InterventionController {


    private IInterventionService interventionService;
    private IProjectService projectService;
    private IPersonService personService;


    public InterventionController(IInterventionService interventionService, IProjectService projectService,
                                  IPersonService personService, InterventionRepository interventionRepository) {
        this.projectService = projectService;
        this.personService = personService;
        this.interventionService = interventionService;
    }

    @GetMapping("/")
    public List<Intervention> getAllIntervention() {
        return interventionService.findAll();
    }

    @PostMapping("/project/{projectId}/person/{personId}")
    public Object createIntervention(@Valid @RequestBody InterventionForm interventionForm,
                                     @PathVariable(value = "projectId") Long projectId
            , @PathVariable(value = "personId") Long personId) {

        return interventionService.saveInterventions(interventionForm, personId, projectId);
    }

    @GetMapping("/{id}")
    public Intervention findInterventionById(@PathVariable(value = "id") Long interventionId) {
        return interventionService.findById(interventionId);
    }

    @GetMapping("/project/{projectId}/person/{personId}")
    List<Intervention> getInterventionByPersonAndProject(@PathVariable(value = "projectId") Long projectId,
                                                         @PathVariable(value = "personId") Long personId) {
        return interventionService.findByPersonAndProject(projectId, personId);
    }

    @GetMapping("/worked/project/{projectId}/person/{personId}")
    Float getworkedByPersonAndProject(@PathVariable(value = "projectId") Long projectId,
                                     @PathVariable(value = "personId") Long personId) {
        return interventionService.workedDayByPersonAndProject(projectId, personId);
    }

    @GetMapping("{projectId}/{personId}/{monthNumber}/{yearNumber}")
    double getworkedByPersonAndProjectByMonth(@PathVariable(value = "projectId") Long projectId,
                                            @PathVariable(value = "personId") Long personId,
                                            @PathVariable(value = "monthNumber") Long monthNumber,
                                            @PathVariable(value = "yearNumber") Long yearNumber) {
        return interventionService.workedDayByPersonAndProjectByMonth(projectId, personId, monthNumber, yearNumber);
    }

    @DeleteMapping("/person/{personId}/project/{projectId}")
    public void deleteInterventionByIdPersonAndProject(@PathVariable(value = "personId") Long personId,
                                                       @PathVariable(value = "projectId") Long projectId) {
        interventionService.deleteIntervention(personId, projectId);
    }

    @DeleteMapping("/{id}")
    public void deleteInterventionById(@PathVariable(value = "id") Long id) {
        interventionService.deleteInterventionHistorique(id);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class InterventionForm {
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date startDate;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date endDate;
        private Person person;
        private Project project;
    }
}
