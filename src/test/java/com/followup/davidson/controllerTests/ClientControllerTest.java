package com.followup.davidson.controllerTests;


import com.followup.davidson.Routes;
import com.followup.davidson.controllers.ClientController;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.services.IClientService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientControllerTest {

    private static Client c1;
    private static Client c2;

    @MockBean
    private IClientService clientService;

    @Autowired
    private ClientController clientController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        c1 = new Client(1L, "Wajdi", "Jaziri");
        c2 = new Client(2L, "test", "test");
    }

    private ClientDto getClientDto() {
        return ClientDto.builder()
                .clientId(1L)
                .clientName("EverySense")
                .clientContact("everysense@gmail.com")
                .build();
    }

    @Test
    void findAll_whenNoRecord() {

        when(clientService.findAll()).thenReturn(Arrays.asList());
        assertThat(clientController.getAllClient().size(), is(0));
        verify(clientService, times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {
        when(clientService.findAll()).thenReturn(Arrays.asList(c1, c2));
        assertThat(clientController.getAllClient().size(), is(2));
        verify(clientService, times(1)).findAll();
    }

    @Test
    void createClientTest(){
        //entry
        ClientDto clientEntry = getClientDto();
        //expected
        ClientDto clientExpceted = getClientDto();
        //Mocks
        Mockito.when(clientService.createOrUpdate(clientEntry))
                .thenReturn(clientExpceted);
        //call
        ClientDto effective=clientController.createClient(clientEntry);
        //Asset
        assertEquals(clientExpceted, effective);
        Mockito.verify(clientService, Mockito.times(1)).createOrUpdate(clientEntry);

    }
    @Test
    void updateClientTest(){
        //entry
        ClientDto clientEntry = getClientDto();
        //expected
        ClientDto clientExpceted = getClientDto();
        //Mocks
        Mockito.when(clientService.createOrUpdate(clientEntry))
                .thenReturn(clientExpceted);
        //call
        ClientDto effective=clientController.updateClient(clientEntry);
        //Asset
        assertEquals(clientExpceted, effective);

    }

    @Test
    void findById_WhenMatch() {
        Mockito.when(clientService.findById(1L)).thenReturn(c1);
        Client c = clientController.findClientById(1L);
        assertThat(c, is(c1));
    }

    @Test
    void deleteById_WhenFound() {
        Mockito.when(clientService.findById(1L)).thenReturn(c1);
        clientController.deleteClient(1L);
        verify(clientService, times(1)).deleteClient(1L);

    }




}
