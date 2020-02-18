package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.ITJService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(Routes.TJ)
public class TJController {


    private ITJService tjService;

    public TJController(ITJService tjService) {
        this.tjService = tjService;
    }

    @GetMapping("/")
    public List<TJ> getAllTj() {
        return tjService.findAll();
    }

    @PostMapping("/")
    public TjDto createTj(@Valid @RequestBody TjDto tjDto) {
        return tjService.create(tjDto);
    }

    @PutMapping("/")
    public TjDto updateTj(@Valid @RequestBody TjDto tjDto) {
        return tjService.updateByProjectAndPerson(tjDto);
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
