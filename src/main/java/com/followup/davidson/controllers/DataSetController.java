package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Dataset;
import com.followup.davidson.services.IDatasetService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Routes.Data)
public class DataSetController {

    private IDatasetService datasetService;

    public DataSetController(IDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/{projectId}")
    public Dataset getByProject(@PathVariable(value = "projectId") Long projectId) {
            return datasetService.getByProject(projectId);
    }
}
