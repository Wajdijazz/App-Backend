package com.followup.davidson.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDto {

    private Long clientId;
    private String clientName;
    private String clientContact;
}
