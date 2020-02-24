package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.ITJService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.TJ)
public class TJController {


    private ITJService tjService;

    public TJController(ITJService tjService) {
        this.tjService = tjService;
    }


    @PostMapping("/")
    public TjDto createTj(@Valid @RequestBody TjDto tjDto) {
        return tjService.create(tjDto);
    }

    @PutMapping("/")
    public TjDto updateTj(@Valid @RequestBody TjDto tjDto) {
        return tjService.updateByProjectAndPerson(tjDto);
    }


    @GetMapping("/{projectId}/{personId}")
    public Float findTarif(@PathVariable(value = "projectId") Long projectId,
                          @PathVariable(value = "personId") Long personId) {
        return tjService.findTarifByProject_ProjectIdAndPerson_PersonId(projectId, personId);
    }

    @DeleteMapping("/{id}")
    public void deleteTj(@PathVariable(value = "id") Long tjId) {
        tjService.deleteTj(tjId);
    }

}
