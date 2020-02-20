package com.followup.davidson.servicesTests;


import com.followup.davidson.converter.ClientConverter;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.services.implementation.ClientServiceImpl;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceTest {


    @SpyBean
    @Autowired
    private ClientConverter clientConverter;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceImpl clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {

    }

    private Client getClient(Long id, String clientName, String clientContact) {
        return Client.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }

    private ClientDto getClientDto(Long id, String clientName, String clientContact) {
        return ClientDto.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }

    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(clientService.findAll().size(), is(0));
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findAllTest_WhenRecord() {
        List<Client> clientListExpected = new ArrayList<>();
        clientListExpected.add(getClient(1L, "EverySense", "everysense@gmail.com"));
        clientListExpected.add(getClient(2L, "Elivia", "elivia@gamil.com"));
        Mockito.when(clientRepository.findAll()).thenReturn(clientListExpected);
        List<Client> clientList = clientService.findAll();
        assertEquals(clientList, clientListExpected);
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findClientByIdWhenAReponseIsThere() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(getClient(1L, "EverySense", "everysense@gmail.com")));
        Client clientExcepted = clientService.findById(1L);
        assertEquals(clientExcepted, getClient(1L, "EverySense", "everysense@gmail.com"));
        Mockito.verify(clientRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        clientService.deleteClient(1L);
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testCreateOrUpdateClient() {
        ClientDto clientDto = getClientDto(1L, "EverySense", "everysense@gmail.com");
        Mockito.when(clientConverter.dtoToEntity(clientDto))
                .thenReturn(getClient(1L, "EverySense", "everysense@gmail.com"));
        Client clientEntryRepository = clientConverter.dtoToEntity(clientDto);
        ClientDto expected = getClientDto(1L, "EverySense", "everysense@gmail.com");
        Mockito.when(clientRepository.save(clientEntryRepository)).thenReturn(clientEntryRepository);
        ClientDto clientDtoReturned = clientService.createOrUpdate(clientDto);
        assertEquals(expected, clientDtoReturned);
        Mockito.verify(clientRepository, Mockito.times(1)).save(clientConverter.dtoToEntity(clientDto));
    }
}
