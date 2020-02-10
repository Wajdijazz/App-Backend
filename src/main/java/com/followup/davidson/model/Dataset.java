package com.followup.davidson.model;


import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dataset {
    private List<PersonDto> persons;
    private ProjectDto project;

}
