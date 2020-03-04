package com.followup.davidson.dto;


import lombok.*;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DatasetDto {
    private Long personId;
    private Long projectId;
    private Float tarif;
    private Float worked;



}
