package com.followup.davidson.servicesTests;


import com.followup.davidson.Utils.Utils;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private Utils utils;

    private final static String CLIENT_1_NAME = "EverySense";
    private final static String CLIENT_1_EMAIL = "everysense@gmail.com";
    private ClientDto CLIENT_DTO_1 = utils.getClientDto(1L, CLIENT_1_NAME, CLIENT_1_EMAIL);
    private Client CLIENT_1 = utils.getClient(1L, CLIENT_1_NAME, CLIENT_1_EMAIL);
    private Client CLIENT_2 = utils.getClient(2L, "Elivia", "elivia@gamil.com");

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

    @Test
    public void findAllTest_WhenNoRecord() {
        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(clientService.findAll().size(), is(0));
    }

    @Test
    public void findAllTest_WhenRecord() {
        List<Client> clientListExpected = Arrays.asList(CLIENT_1, CLIENT_2);

        Mockito.when(clientRepository.findAll()).thenReturn(clientListExpected);

        Collection<ClientDto> clientList = clientService.findAll();
        assertEquals(clientList, clientListExpected);
    }

    @Test
    public void findClientByIdWhenAReponseIsThere() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(CLIENT_1));
        ClientDto clientExcepted = clientService.findById(1L);
        assertEquals(clientExcepted, CLIENT_1);
        Mockito.verify(clientRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testCreateOrUpdateClient() {
        Mockito.when(clientRepository.save(CLIENT_1)).thenReturn(CLIENT_1);

        ClientDto clientDtoReturned = clientService.createOrUpdate(CLIENT_DTO_1);

        assertEquals(CLIENT_DTO_1, clientDtoReturned);
        Mockito.verify(clientRepository, Mockito.times(1)).save(clientConverter.dtoToEntity(CLIENT_DTO_1));
    }
}
