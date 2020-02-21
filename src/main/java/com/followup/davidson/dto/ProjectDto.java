package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectDto {
    private Long projectId;
    private String projectName;
    private Long clientId;
    private Long managerId;
    private ManagerDto manager;
    private ClientDto client;
}
