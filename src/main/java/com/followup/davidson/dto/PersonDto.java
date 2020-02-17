package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {
    private Long personId;
    private String firstName;
    private String lastName;

}
