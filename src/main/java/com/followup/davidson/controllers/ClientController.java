package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.services.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Routes.CLIENT)
public class ClientController {

    private IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getAllClient() {
        return clientService.findAll();
    }


    @PostMapping("/")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        clientService.create(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{clientId}")
                .buildAndExpand(client.getClientId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "clientId") Long clientId, @Valid @RequestBody Client client) {
        clientService.updateClient(clientId, client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{clientId}")
                .buildAndExpand(client.getClientId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Optional<Client> findClientById(@PathVariable(value = "id") Long clientId) {
        return clientService.findById(clientId);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") Long clientId) {
        clientService.deleteClient(clientId);
    }

}
