package com.followup.davidson.services;

import com.followup.davidson.dto.DashboardDto;

import java.util.List;

public interface IDashboardService {

    DashboardDto createDashboard(Long projectId, Long personId, Float tarif);
}
