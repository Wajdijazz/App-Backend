package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.TJ)
@AllArgsConstructor
public class TJController {


    private ITJService tjService;
    private IDashboardService dashboardService;



    @PostMapping("/project/{projectId}/person/{personId}")
    public TjDto createTj(@Valid @RequestBody TjDto tjDto,@PathVariable(value = "projectId") Long projectId,
                          @PathVariable(value = "personId") Long personId) {

        return tjService.create(tjDto,projectId,personId);
    }

    @PutMapping("/project/{projectId}/person/{personId}")
    public TjDto updateTj(@Valid @RequestBody TjDto tjDto,
                          @PathVariable(value = "projectId") Long projectId,
                          @PathVariable(value = "personId") Long personId) {
        return tjService.updateByProjectAndPerson(tjDto,projectId,personId);
    }


    @GetMapping("/{projectId}/{personId}")
    public Float findTarif(@PathVariable(value = "projectId") Long projectId,
                          @PathVariable(value = "personId") Long personId) {
        return tjService.findTarifByProject_ProjectIdAndPerson_PersonId(projectId, personId);
    }

    @DeleteMapping("/project/{projectId}")
    public void deleteTjByProject(@PathVariable(value = "projectId") Long projectId) {
        tjService.deleteTjByProject(projectId);
    }

    @DeleteMapping("/person/{personId}")
    public void deleteTjByPerson(@PathVariable(value = "personId") Long personId) {
        tjService.deleteTjByPerson(personId);
    }

}
