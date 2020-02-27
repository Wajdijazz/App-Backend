package com.followup.davidson.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.followup.davidson.model.Mode;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterventionDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Mode mode;
    private PersonDto person;
    private ProjectDto project;
}
