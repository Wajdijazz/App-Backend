package com.followup.davidson.dto;


import lombok.*;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DatasetDto {
    private Collection<PersonDto> persons;
    private Collection<ProjectDto> projects;
    private ProjectDto project;


}
