package com.followup.davidson.servicesTests;

import com.followup.davidson.Utils.DataForTest;
import com.followup.davidson.Utils.Utils;
import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.dto.*;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.implementation.DataSetServiceImp;
import com.followup.davidson.services.implementation.PersonServiceImpl;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
import com.followup.davidson.services.implementation.TJServiceImpl;
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

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DataSetServiceTest {

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

    private PersonDto PERSON_DTO_1 = utils.getPersonDto(
            DataForTest.PersonData.PERSON_1_ID,
            DataForTest.PersonData.PERSON_1_FIRSTNAME,
            DataForTest.PersonData.PERSON_1_LASTTNAME,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            DataForTest.PersonData.PERSON_1_ISACTIVE
    );

    private PersonDto PERSON_DTO_2 = utils.getPersonDto(
            DataForTest.PersonData.PERSON_2_ID,
            DataForTest.PersonData.PERSON_2_FIRSTNAME,
            DataForTest.PersonData.PERSON_2_LASTTNAME,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            DataForTest.PersonData.PERSON_1_ISACTIVE
    );

    private ProjectDto PROJECT_DTO_1 = utils.getProjectDto(
            DataForTest.ProjectData.PROJECT_1_ID,
            DataForTest.ProjectData.PROJECT_1_NAME,
            DataForTest.ClientData.CLIENT_1_ID,
            DataForTest.ManagerData.MANAGER_1_ID,
            MANAGER_DTO,
            CLIENT_DTO,
            DataForTest.ProjectData.PROJECT_1_ISACTIVE
    );

    @SpyBean
    @Autowired
    private PersonConverter personConverter;

    @SpyBean
    @Autowired
    private ProjectConverter projectConverter;

    @SpyBean
    @Autowired
    private DataSetServiceImp dataSetService;

    @MockBean
    private PersonServiceImpl personService;

    @MockBean
    ProjectServiceImpl projectService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private List<PersonDto> getPersonsActive() {
        List<PersonDto> persons = personService.findAll();

        return persons.stream()
                .filter(personDto -> personDto.isActive() == true)
                .collect(Collectors.toList());
    }

    @Test
    public void getByProjectTest() {
        List<PersonDto> personListExcepted = new ArrayList<>();

        personListExcepted.add(PERSON_DTO_1);
        personListExcepted.add(PERSON_DTO_2);


        Mockito.when(personService.findAll()).thenReturn(personListExcepted);
        Mockito.when(projectService.findByProjectIdAndIsActiveTrue(1L)).thenReturn(PROJECT_DTO_1);


        List<PersonDto> personDtoList =getPersonsActive();

                DatasetDto datasetExpected = DatasetDto.builder()
                .persons(personDtoList)
                .project(PROJECT_DTO_1)
                .build();

        DatasetDto datasetEntry = dataSetService.getByProject(1L);
        assertEquals(datasetExpected, datasetEntry);

    }
}

