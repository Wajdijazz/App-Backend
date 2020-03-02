package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.services.IDatasetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.DATA)
public class DataSetController {

    private IDatasetService datasetService;

    public DataSetController(IDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/{projectId}")
    public DatasetDto getByProject(@PathVariable(value = "projectId") Long projectId) {
            return datasetService.getByProject(projectId);
    }
    @GetMapping("/")
    public DatasetDto getByProjectPersons() {
            return datasetService.getByProjectPersons();
    }
}
