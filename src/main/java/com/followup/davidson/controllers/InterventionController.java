package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.InterventionDto;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.services.IInterventionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(Routes.INTERVENTION)
public class InterventionController {


    private IInterventionService interventionService;


    public InterventionController(IInterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @GetMapping("/")
    public List<Intervention> getAllIntervention() {
        return interventionService.findAll();
    }

    @PostMapping("/project/{projectId}/person/{personId}")
    public Object createIntervention(@Valid @RequestBody List<InterventionDto> interventionDto){

        return interventionService.saveInterventions(interventionDto);
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

}
