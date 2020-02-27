package com.followup.davidson.controllers;

import com.followup.davidson.Routes;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.DASHBOARD)
@AllArgsConstructor
public class DashboardController {
    private IDashboardService dashboardService;
    @PostMapping("/")
    public DashboardDto createDashboard(@Valid @RequestBody DashboardDto dashboardDto) {
        return dashboardService.createDashboard(dashboardDto);
    }
}
