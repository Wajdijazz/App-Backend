package com.followup.davidson.servicesTests;

import com.followup.davidson.Utils.DataForTest;
import com.followup.davidson.Utils.Utils;
import com.followup.davidson.dto.*;
import com.followup.davidson.model.*;
import com.followup.davidson.repositories.ClientRepository;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjServiceTest {

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

    private ProjectDto PROJECT_DTO_1 = utils.getProjectDto(
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.ProjectData.PROJECT_1_NAME,
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            CLIENT_DTO,
            DataForTest.ProjectData.PROJECT_1_ISACTIVE
    );
    private Manager MANAGER = utils.getManager(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);

    private Client CLIENT = utils.getClient(
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ClientData.CLIENT_1_NAME,
            DataForTest.ClientData.CLIENT_1_EMAIL);
    private Project PROJECT_1 = utils.getProject(
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.ProjectData.PROJECT_1_NAME,
            MANAGER,
            CLIENT,
            DataForTest.ProjectData.PROJECT_1_ISACTIVE
    );
    private PersonDto PERSON_DTO_1 = utils.getPersonDto(
            DataForTest.PersonData.PERSON_1_ID,
            DataForTest.PersonData.PERSON_1_FIRSTNAME,
            DataForTest.PersonData.PERSON_1_LASTTNAME,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            DataForTest.PersonData.PERSON_1_ISACTIVE
    );

    private Person PERSON_1 = utils.getPerson(
            DataForTest.PersonData.PERSON_1_ID,
            DataForTest.PersonData.PERSON_1_FIRSTNAME,
            DataForTest.PersonData.PERSON_1_LASTTNAME,
            MANAGER,
            DataForTest.PersonData.PERSON_1_ISACTIVE
    );

    private TJ TJ_1 = utils.getTj(
            DataForTest.TjData.TJ_I_ID,
            DataForTest.TjData.TARIF,
            PROJECT_1,
            PERSON_1
    );
    private TjDto TJ_DTO_1 = utils.getTjDto(
            DataForTest.TjData.TJ_I_ID,
            DataForTest.TjData.TARIF,
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.PersonData.PERSON_1_ID,
            PROJECT_DTO_1,
            PERSON_DTO_1
    );

    @MockBean
    private IProjectService projectService;

    @MockBean
    private IPersonService personService;

    @MockBean
    private TJRepository tjRepository;

    @Autowired
    private ITJService tjItjService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createTjTest() {
        Mockito.when(projectService.findById(1L)).thenReturn(PROJECT_DTO_1);
        Mockito.when(personService.findById(1L)).thenReturn(PERSON_DTO_1);
        Mockito.when(tjRepository.save(TJ_1)).thenReturn(TJ_1);

        TjDto tjDtoReturned = tjItjService.create(TJ_DTO_1);
        assertEquals(TJ_DTO_1, tjDtoReturned);
    }

    @Test
    public void updateByProjectAndPersonTest() {
        Mockito.when(projectService.findById(1L)).thenReturn(PROJECT_DTO_1);
        Mockito.when(personService.findById(1L)).thenReturn(PERSON_DTO_1);

        Mockito.when(tjRepository.findByProject_ProjectIdAndPerson_PersonId(PROJECT_DTO_1.getProjectId(),
                PERSON_DTO_1.getPersonId())).thenReturn(TJ_1);

        Mockito.when(tjRepository.save(TJ_1)).thenReturn(TJ_1);

        TjDto tjDtoReturned = tjItjService.updateByProjectAndPerson(TJ_DTO_1);
        assertEquals(TJ_DTO_1, tjDtoReturned);


    }
}
