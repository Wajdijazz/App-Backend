package com.followup.davidson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private ManagerDto managerDto;
    private ClientDto clientDto;
}
