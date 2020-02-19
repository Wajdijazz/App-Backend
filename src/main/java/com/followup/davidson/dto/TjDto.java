package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TjDto {
    private Long tjId;
    private Float tarif;
    private Long personId;
    private Long projectId;
}
