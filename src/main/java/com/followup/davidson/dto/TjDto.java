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
    private ProjectDto projectDto;
    private PersonDto personDto;
}
