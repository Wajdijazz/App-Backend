package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.services.IClientService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(Routes.CLIENT)
public class ClientController {

    private IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<ClientDto> getAllClient() {
        return clientService.findAll();
    }

    @PostMapping("/")
    public ClientDto createClient(@Valid @RequestBody ClientDto clientDto) {
       return clientService.createOrUpdate(clientDto);
    }
    @PutMapping("/")
    public ClientDto updateClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.createOrUpdate(clientDto);
    }
    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable(value = "id") Long clientId)
    {
        return clientService.findById(clientId);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") Long clientId) {
        clientService.deleteClient(clientId);
    }

}
