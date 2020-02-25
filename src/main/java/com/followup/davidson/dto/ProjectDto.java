package com.followup.davidson.dto;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProjectDto {
    private Long projectId;
    private String projectName;
    private Long clientId;
    private Long managerId;
    private ManagerDto managerDto;
    private ClientDto clientDto;
}
