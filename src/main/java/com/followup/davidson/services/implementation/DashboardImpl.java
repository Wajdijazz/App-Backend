package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.DashboardConverter;
import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.repositories.DashboardRepository;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@AllArgsConstructor
@Service
public class DashboardImpl implements IDashboardService {
    private IProjectService projectService;
    private IPersonService personService;
    private DashboardConverter dashboardConverter;
    private DashboardRepository dashboardRepository;

    @Override
    public DashboardDto createDashboard(DashboardDto dashboardDto) {
        dashboardDto.setProjectDto(projectService.findById(dashboardDto.getProjectId()));
        dashboardDto.setPersonDto(personService.findById(dashboardDto.getPersonId()));
        return dashboardConverter.entityToDto(dashboardRepository.save(dashboardConverter.dtoToEntity(dashboardDto)));
    }
}
