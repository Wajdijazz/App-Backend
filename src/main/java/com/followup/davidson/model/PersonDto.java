package com.followup.davidson.model;

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
    private Long price;
    private Long worked;
}
