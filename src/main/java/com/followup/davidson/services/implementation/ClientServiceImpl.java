package com.followup.davidson.services.implementation;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.services.IClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClientServiceImpl implements IClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Cette methode permet de lister tous les clients de davidsons
     * @return une liste des {@link Client}
     */
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Cette methode permet de retourner un client par id
     *
     * @param id
     * @return un client
     */
    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Cette methode permet de créer et sauvgarder un nouveau client
     * @param client
     * @return client crée
     */
    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client client) {
        Client clientUp=new Client().builder()
                .clientId(clientId)
                .clientName(client.getClientName())
                .clientContact(client.getClientContact())
                .build();
        clientRepository.save(clientUp);
        return clientUp;
    }

    /**
     * Cette methode permet de supprimer un client par son id
     *
     * @param id
     */
    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
