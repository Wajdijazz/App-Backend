package com.followup.davidson.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DashboardDto {
    private Long dashboardId;
    private ProjectDto projectDto;
    private PersonDto personDto;
    private Float tarif;
    private Float worked_day;
    private float total;
}
