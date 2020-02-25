package com.followup.davidson.dto;

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
    private Long managerId;
    private ManagerDto managerDto;

}
