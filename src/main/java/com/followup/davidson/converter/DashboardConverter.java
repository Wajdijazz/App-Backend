package com.followup.davidson.converter;

import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.model.Dashboard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DashboardConverter  implements GenericsConverter<Dashboard, DashboardDto> {
    private PersonConverter personConverter;
    private ProjectConverter projectConverter;
    @Override
    public DashboardDto entityToDto(Dashboard dashboard) {
        return DashboardDto.builder()
                .dashboardId(dashboard.getDashboardId())
                .personDto(personConverter.entityToDto(dashboard.getPerson()))
                .projectDto(projectConverter.entityToDto(dashboard.getProject()))
                .tarif(dashboard.getTarif())
                .worked_day(dashboard.getWorked_day())
                .total(dashboard.getTotal())
                .build();
    }

    @Override
    public Dashboard dtoToEntity(DashboardDto dashboardDto) {
        return Dashboard.builder()
                .dashboardId(dashboardDto.getDashboardId())
                .person(personConverter.dtoToEntity(dashboardDto.getPersonDto()))
                .project(projectConverter.dtoToEntity(dashboardDto.getProjectDto()))
                .tarif(dashboardDto.getTarif())
                .worked_day(dashboardDto.getWorked_day())
                .total(dashboardDto.getTotal())
                .build();    }
}
