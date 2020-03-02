package com.followup.davidson.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonDto {
    private Long personId;
    private String firstName;
    private String lastName;
    private ManagerDto managerDto;
    @JsonProperty
    private boolean isActive;
    private float tarif;

}
