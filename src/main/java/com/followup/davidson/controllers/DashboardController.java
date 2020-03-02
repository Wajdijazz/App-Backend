package com.followup.davidson.controllers;

import com.followup.davidson.Routes;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.DASHBOARD)
@AllArgsConstructor
public class DashboardController {
    private IDashboardService dashboardService;

}
