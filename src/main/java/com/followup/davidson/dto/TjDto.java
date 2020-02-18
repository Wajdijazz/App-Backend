package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TjDto {
    private Long tjId;
    private double tarif;
    private Long personId;
    private Long projectId;
}
