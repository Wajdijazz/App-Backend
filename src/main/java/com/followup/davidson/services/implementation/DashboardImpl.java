package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.DashboardConverter;
import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.repositories.DashboardRepository;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.IInterventionService;
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
    private IInterventionService interventionService;



    @Override
    public DashboardDto createDashboard(Long projectId, Long personId, Float tarif) {
        PersonDto personDto=personService.findById(personId);
        ProjectDto projectDto=projectService.findById(projectId);
        Float worked_day=interventionService.workedDayByPersonAndProject(projectId,personId);
        DashboardDto dashboardDto=DashboardDto.builder()
                .personDto(personDto)
                .projectDto(projectDto)
                .tarif(tarif)
                .worked_day(worked_day)
                .build();
        return dashboardConverter.entityToDto(dashboardRepository.save(dashboardConverter.dtoToEntity(dashboardDto)));
    }
}
