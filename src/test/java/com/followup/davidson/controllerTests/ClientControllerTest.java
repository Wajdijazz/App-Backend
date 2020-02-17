package com.followup.davidson.controllerTests;


import com.followup.davidson.controllers.ClientController;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientControllerTest {

    private static Client c1;
    private static Client c2;

    @Mock
    private IClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        c1 = new Client(1L, "Wajdi", "Jaziri");
        c2 = new Client(2L, "test", "test");
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

  /*  @Test
    void createOnClickAddClient() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(clientService.create(any(Client.class))).thenReturn(c1);
        ResponseEntity<Client> responseEntity = clientController.createClient(c1);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    void updateOnClickUpdateClient() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(clientService.updateClient(anyLong(), any(Client.class))).thenReturn(c1);
        ResponseEntity<Client> responseEntity = clientController.updateClient(1L,c1);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
*/
    @Test
    void findById_WhenMatch() {
        when(clientService.findById(1L)).thenReturn(c1);
       Client c = clientController.findClientById(1L);
        assertThat(c, is(c1));
    }

    @Test
    void deleteById_WhenFound() {

        lenient().when(clientService.findById(1L)).thenReturn(c1);
        clientController.deleteClient(1L);
        verify(clientService, times(1)).deleteClient(1L);

    }

}
