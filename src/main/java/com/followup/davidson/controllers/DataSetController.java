package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.services.IDatasetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.DATA)
public class DataSetController {

    private IDatasetService datasetService;

    public DataSetController(IDatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/month/{month}/year/{year}")
    public List<DatasetDto> getDataSet(@PathVariable(value = "month") int month, @PathVariable(value = "year") int year) {
        return datasetService.getDataSet(month, year);
    }

    @GetMapping("/tj")
    public List<DatasetDto> getDataSetForTj() {
        return datasetService.getDataSetForTj();
    }

}
