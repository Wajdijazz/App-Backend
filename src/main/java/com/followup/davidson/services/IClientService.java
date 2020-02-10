package com.followup.davidson.services;

import com.followup.davidson.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client create(Client client);

    Client updateClient(Long clientId,Client client);

    void deleteClient(Long id);
}
