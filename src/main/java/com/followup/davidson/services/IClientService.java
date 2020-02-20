package com.followup.davidson.services;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.model.Client;
import java.util.List;

public interface IClientService {

    List<Client> findAll();

    Client findById(Long id);

    ClientDto createOrUpdate(ClientDto clientDto);

    void deleteClient(Long id);
}
