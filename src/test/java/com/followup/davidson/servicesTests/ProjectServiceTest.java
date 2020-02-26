package com.followup.davidson.servicesTests;

import com.followup.davidson.Utils.DataForTest;
import com.followup.davidson.Utils.Utils;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Client;

import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.IClientService;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
import org.junit.Before;
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
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private Utils utils;

    private ManagerDto MANAGER_DTO = utils.getManagerDto(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);

    private ClientDto CLIENT_DTO = utils.getClientDto(
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ClientData.CLIENT_1_NAME,
            DataForTest.ClientData.CLIENT_1_EMAIL);

    private Manager MANAGER = utils.getManager(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);

    private Client CLIENT = utils.getClient(
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ClientData.CLIENT_1_NAME,
            DataForTest.ClientData.CLIENT_1_EMAIL);

    private ProjectDto PROJECT_DTO_1 = utils.getProjectDto(
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.ProjectData.PROJECT_1_NAME,
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            CLIENT_DTO,
            DataForTest.ProjectData.PROJECT_1_ISACTIVE
    );
    private ProjectDto PROJECT_DTO_2 = utils.getProjectDto(
            DataForTest.ProjectData.PROJECT_2_ID,
            DataForTest.ProjectData.PROJECT_2_NAME,
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            CLIENT_DTO,
            DataForTest.ProjectData.PROJECT_2_ISACTIVE
    );

    private Project PROJECT_1 = utils.getProject(
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.ProjectData.PROJECT_1_NAME,
            MANAGER,
            CLIENT,
            DataForTest.ProjectData.PROJECT_1_ISACTIVE
    );

    private Project PROJECT_2 = utils.getProject(
            DataForTest.ProjectData.PROJECT_2_ID,
            DataForTest.ProjectData.PROJECT_2_NAME,
            MANAGER,
            CLIENT,
            DataForTest.ProjectData.PROJECT_2_ISACTIVE
    );


    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private IProjectService projectService;

    @SpyBean
    @Autowired
    private IClientService clientService;

    @SpyBean
    @Autowired
    private IManagerService managerService;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private ClientRepository clientRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(projectRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(projectService.findAll().size(), is(0));
        Mockito.verify(projectRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {

        List<Project> projectListExpected = Arrays.asList(PROJECT_1, PROJECT_2);
        Mockito.when(projectRepository.findAll()).thenReturn(projectListExpected);

        List<ProjectDto> projectListDtoExpected = Arrays.asList(PROJECT_DTO_1, PROJECT_DTO_2);
        List<ProjectDto> projectDtoList = projectService.findAll();

        assertEquals(projectDtoList, projectListDtoExpected);
    }

    @Test
    public void findById() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(PROJECT_1));
        ProjectDto projectDtoExcepted = projectService.findById(1L);
        assertEquals(projectDtoExcepted, PROJECT_DTO_1);
    }

    @Test
    void testCreateOrUpdateProject() {
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.of(MANAGER));
        ManagerDto managerExcepted = managerService.findById(1L);
        assertEquals(managerExcepted, MANAGER_DTO);

        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(CLIENT));
        ClientDto clientDtoExcepted = clientService.findById(1L);
        assertEquals(clientDtoExcepted, CLIENT_DTO);

        Mockito.when(projectRepository.save(PROJECT_1)).thenReturn(PROJECT_1);
        ProjectDto projectDtoReturned = projectService.createOrUpdate(PROJECT_DTO_1);

        assertEquals(PROJECT_DTO_1, projectDtoReturned);
    }

    @Test
    void updateIsActiveByPersonIdTest() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(PROJECT_1));

        Mockito.when(projectRepository.save(PROJECT_1)).thenReturn(PROJECT_1);

        ProjectDto personDtoReturned = projectService
                .updateIsActiveByProjectId(PROJECT_1.getProjectId(), PROJECT_1.isActive());

        assertEquals(PROJECT_DTO_1, personDtoReturned);

    }
}
