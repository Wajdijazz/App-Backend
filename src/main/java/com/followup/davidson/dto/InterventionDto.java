package com.followup.davidson.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterventionDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Person person;
    private Project project;
}
