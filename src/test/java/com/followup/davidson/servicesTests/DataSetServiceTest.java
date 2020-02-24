package com.followup.davidson.servicesTests;

import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
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

    private PersonDto getPersonDto(Long id, String firstName, String lastName) {
        return PersonDto.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private ProjectDto getProjectDto(Long id, String projectName) {
        return ProjectDto.builder()
                .projectId(id)
                .projectName(projectName)
                .build();
    }

    private Person getPerson(Long id, String firstName, String lastName) {
        return Person.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private Project getProject(Long id, String projectName) {
        return Project.builder()
                .projectId(id)
                .projectName(projectName)
                .build();
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByProjectTest() {
        Collection<PersonDto> personListExcepted = new ArrayList<>();

        personListExcepted.add(getPersonDto(1L, "Wajdi", "Jaziri"));
        personListExcepted.add(getPersonDto(2L, "Noe", "Pamula"));


        Mockito.when(personService.findAll()).thenReturn(personListExcepted);
        Mockito.when(projectService.findById(1L)).thenReturn(getProjectDto(1L, "Follow-up"));

        Collection<PersonDto> persons = personService.findAll();

        PersonDto personDto = getPersonDto(1L, "Wajdi", "Jaziri");
        PersonDto personDto1 = getPersonDto(2L, "Noe", "Pamula");


        List<PersonDto> personDtos = new ArrayList<>();
        personDtos.add(personDto);
        personDtos.add(personDto1);

        Mockito.when(persons)
                .thenReturn(personDtos);

        Mockito.when(projectConverter.entityToDto(getProject(1L, "Follow-up")))
                .thenReturn(getProjectDto(1L, "Follow-up"));

        DatasetDto datasetExpected = DatasetDto.builder()
                .persons(persons)
                .project(projectConverter.entityToDto(getProject(1L, "Follow-up")))
                .build();

        DatasetDto datasetEntry = dataSetService.getByProject(1L);
        assertEquals(datasetExpected, datasetEntry);

        Mockito.verify(personService, Mockito.times(2)).findAll();
        Mockito.verify(projectService, Mockito.times(1)).findById(1L);
    }
}

