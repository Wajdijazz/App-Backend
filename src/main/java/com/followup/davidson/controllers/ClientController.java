package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.services.IClientService;
import lombok.AllArgsConstructor;
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
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable(value = "clientId") Long clientId, @Valid @RequestBody Client client) {
       return clientService.updateClient(clientId, client);
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable(value = "id") Long clientId) {
        return clientService.findById(clientId);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") Long clientId) {
        clientService.deleteClient(clientId);
    }

}
