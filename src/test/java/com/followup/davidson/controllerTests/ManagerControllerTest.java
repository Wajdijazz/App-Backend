package com.followup.davidson.controllerTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.followup.davidson.controllers.ManagerController;
import com.followup.davidson.model.Manager;
import com.followup.davidson.services.IManagerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {
    private static Manager m1;
    private static Manager m2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IManagerService managerService;

    @InjectMocks
    private ManagerController managerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        m1 = new Manager(1L, "Wajdi", "Jaziri");
        m2 = new Manager(2L, "test", "test");
    }

    @Test
    void findAll_whenNoRecord() {
        when(managerService.findAll()).thenReturn(Arrays.asList());
        assertThat(managerController.getAllManager().size(), is(0));
        Mockito.verify(managerService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {
        when(managerService.findAll()).thenReturn(Arrays.asList(m1, m2));
        assertThat(managerController.getAllManager().size(), is(2));
        Mockito.verify(managerService, Mockito.times(1)).findAll();
    }

    @Test
    void createOnClickAddManager() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(managerService.create(Mockito.any(Manager.class))).thenReturn(m1);
        ResponseEntity<Manager> responseEntity = managerController.createManager(m1);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    void findById_WhenMatch() {
        when(managerService.findById(1L)).thenReturn(Optional.of(m1));
        Optional<Manager> m = managerController.findManagerById(1L);
        assertThat(m.get(), is(m1));
    }

    @Test
    void deleteById_WhenFound() {
        lenient().when(managerService.findById(1L)).thenReturn(Optional.of(m1));
        managerController.deleteManager(1L);
        Mockito.verify(managerService, Mockito.times(1)).deleteManager(1L);
    }


}




