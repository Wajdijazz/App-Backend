package com.followup.davidson.converter;

import com.followup.davidson.dto.DashboardDto;
import com.followup.davidson.model.Dashboard;
import org.springframework.stereotype.Component;

@Component
public class DashboardConverter implements GenericsConverter<Dashboard, DashboardDto> {
    @Override
    public DashboardDto entityToDto(Dashboard dashboard) {
        return DashboardDto.builder()
                .dashboardId(dashboard.getDashboardId())
                .personId(dashboard.getPerson().getPersonId())
                .projectId(dashboard.getProject().getProjectId())
                .tarif(dashboard.getTarif())
                .total(dashboard.getTotal())
                .worked_day(dashboard.getWorked_day())
                .build();
    }

    @Override
    public Dashboard dtoToEntity(DashboardDto dashboardDto) {
        return Dashboard.builder()
                .dashboardId(dashboardDto.getDashboardId())
                .tarif(dashboardDto.getTarif())
                .total(dashboardDto.getTotal())
                .worked_day(dashboardDto.getWorked_day())
                .build();
    }
}
