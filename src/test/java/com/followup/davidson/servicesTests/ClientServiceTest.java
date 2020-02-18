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

    private static Client c1;
    private static Client c2;

    @Autowired
    private ClientConverter clientConverter;

    @MockBean
    private ClientRepository clientRepository;

    @SpyBean
    @Autowired
    private ClientServiceImpl clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        c1 = new Client(1L, "EverySense", "contact@gmail.com");
        c2 = new Client(1L, "Elivia", "elivia@gamil.com");
    }

    private ClientDto getClientDto() {
        return ClientDto.builder()
                .clientId(1L)
                .clientName("EverySense")
                .clientContact("everysense@gmail.com")
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
        List<Client> clientListExcepted = new ArrayList<>();
        clientListExcepted.add(c1);
        clientListExcepted.add(c2);
        Mockito.when(clientRepository.findAll()).thenReturn(clientListExcepted);
        List<Client> clientList=clientService.findAll();
        assertEquals(clientList,clientListExcepted);
        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findClientByIdWhenAReponseIsThere() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(c1));
        Client clientExcepted=clientService.findById(1L);
        assertEquals(clientExcepted, c1);
        Mockito.verify(clientRepository, Mockito.times(1)).findById(1L);
    }


    @Test
    void deleteById() {
        clientService.deleteClient(1L);
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testCreateOrUpdateClient() {
        ClientDto clientDto=getClientDto();
        Client clientEntryRepository=clientConverter.dtoToEntity(clientDto);
        ClientDto excepted=getClientDto();
        excepted.setClientId(1L);
        excepted.setClientName("EverySense");
        excepted.setClientContact("everysense@gmail.com");
        Mockito.when(clientRepository.save(clientEntryRepository)).thenReturn(clientEntryRepository);
        ClientDto clientDtoReturned = clientService.createOrUpdate(clientDto);
        Mockito.verify(clientRepository, Mockito.times(1)).save(clientConverter.dtoToEntity(clientDto));
        assertEquals(excepted, clientDtoReturned);
    }
}
