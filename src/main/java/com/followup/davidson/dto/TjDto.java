package com.followup.davidson.dto;

import com.followup.davidson.model.Project;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TjDto {
    private Long tjId;
    private Float tarif;
    private Long personId;
    private Long projectId;
    private ProjectDto projectDto;
    private PersonDto personDto;
}
