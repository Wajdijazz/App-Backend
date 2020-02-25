package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.ClientConverter;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.services.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class ClientServiceImpl implements IClientService {

    private ClientRepository clientRepository;
    private ClientConverter clientConverter;

    /**
     * Cette methode permet de lister tous les clients de davidsons
     * @return une liste des {@link Client}
     */
    @Override
    public List<ClientDto> findAll() {
        return clientConverter.entityListToDtoList(clientRepository.findAll());
    }

    /**
     * Cette methode permet de retourner un client par id
     *
     * @param id
     * @return un client
     */
    @Override
    public ClientDto findById(Long id) {
        return clientConverter.entityToDto(clientRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("This client with Id" + id + "not exist")));
    }

    @Override
    public ClientDto createOrUpdate(ClientDto clientDto) {
       return clientConverter.entityToDto(clientRepository.save(clientConverter.dtoToEntity(clientDto)));
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
