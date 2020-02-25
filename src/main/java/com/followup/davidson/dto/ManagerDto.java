package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ManagerDto {

    private Long managerId;
    private String firstName;
    private String lastName;
}
