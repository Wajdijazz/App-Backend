package com.followup.davidson.converter;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.model.Client;

import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements GenericsConverter<Client, ClientDto> {
    @Override
    public ClientDto entityToDto(Client client) {
        return ClientDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .clientContact(client.getClientContact())
                .build();
    }

    @Override
    public Client dtoToEntity(ClientDto clientDto) {
        return Client.builder()
                .clientId(clientDto.getClientId())
                .clientName(clientDto.getClientName())
                .clientContact(clientDto.getClientContact())
                .build();
    }
}
