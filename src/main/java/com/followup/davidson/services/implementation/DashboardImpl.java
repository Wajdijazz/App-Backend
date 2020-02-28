package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.DashboardConverter;
import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Dashboard;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.repositories.DashboardRepository;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class DashboardImpl implements IDashboardService {
    private IProjectService projectService;
    private IPersonService personService;
    private DashboardConverter dashboardConverter;
    private DashboardRepository dashboardRepository;

    @Override
    public DashboardDto createDashboard(List<DashboardDto> dashboardDtoList) {
        return null;
    }
}
