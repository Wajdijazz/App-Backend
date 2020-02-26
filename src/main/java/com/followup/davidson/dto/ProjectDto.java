package com.followup.davidson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private boolean isActive;
    private Long clientId;
    private Long managerId;
    private ManagerDto managerDto;
    private ClientDto clientDto;

}
