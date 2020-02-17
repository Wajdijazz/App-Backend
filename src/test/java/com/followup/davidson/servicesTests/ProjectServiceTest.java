package com.followup.davidson.servicesTests;

import com.followup.davidson.controllers.ClientController;
import com.followup.davidson.model.Client;

import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.implementation.ClientServiceImpl;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
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

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProjectServiceTest {
    private static Project p1;
    private static Project p2;
    private static Client c1;


    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;
    @Mock
    private ClientServiceImpl clientService;
    @InjectMocks
    private ClientController clientController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        p1=new Project(1L,"Followup",null);
        p2=new Project(1L,"ERP",null);
        c1=new Client(1L,"Decathlon","decathlon@gmail.com");


    }
    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(projectRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(projectService.findAll().size(), is(0));
        Mockito.verify(projectRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {
        Mockito.when(projectRepository.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(projectService.findAll().size(), is(2));
        assertThat(projectService.findAll().get(0), is(p1));
        assertThat(projectService.findAll().get(1),is(p2));
        Mockito.verify(projectRepository, Mockito.times(3)).findAll();

    }

    @Test
    public void findById() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(p1));
        assertThat(projectService.findById(1L), is(Optional.of(p1)));
        Mockito.verify(projectRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        projectService.deleteProject(1L);
        Mockito.verify(projectRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void create() {
        Mockito.when(clientService.findById(1L)).thenReturn(c1);
        Client c = clientController.findClientById(1L);
        assertThat(c, is(c1) );

        Mockito.when(projectRepository.save(p1)).thenReturn(p1);
        assertThat(projectService.create(p1,1L), is(p1));
        Mockito.verify(projectRepository, Mockito.times(1)).save(p1);

        Mockito.when(projectRepository.save(p2)).thenReturn(p2);
        assertThat(projectService.create(p2,1L), is(p2));
        Mockito.verify(projectRepository, Mockito.times(1)).save(p2);
    }
}
