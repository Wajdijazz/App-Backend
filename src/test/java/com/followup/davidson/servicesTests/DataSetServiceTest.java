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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DataSetServiceTest {
    private static TJ tj1;
    private static TJ tj2;
    private static Project project;
    private static  Person person;
    private static  Person person2;



    @Autowired
    private PersonConverter personConverter;
    @Autowired
    private ProjectConverter projectConverter;
    @SpyBean
    @Autowired
    private DataSetServiceImp dataSetService;

    @MockBean
    private TJServiceImpl tjService;

    @MockBean
    ProjectServiceImpl projectService;
    @BeforeAll
    public static void init() {
        project=new Project(1L,"Followup",null);
        person=new Person(1L,"Wajdi","Jaziri",null);
        person2=new Person(2L,"Test","Test",null);
        tj1 = new TJ(1L, 50f, person, project);
        tj2 = new TJ(1L, 70f, person2, project);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    private DatasetDto getDataset(){
        PersonDto personDto=PersonDto.builder()
                .personId(1L)
                .firstName("Wajdi")
                .lastName("Jaziri")
                .build();
        PersonDto personDto1=PersonDto.builder()
                .personId(2L)
                .firstName("Test")
                .lastName("Test")
                .build();
        ProjectDto projectDto= ProjectDto.builder()
                .projectId(1L)
                .projectName("Followup")
                .build();

        List<PersonDto> persons=new ArrayList<>();
        persons.add(personDto);
        persons.add(personDto1);

        return  DatasetDto.builder()
                .persons(persons)
                .project(projectDto)
                .build();
    }
    @Test
    public void getByProjectTest(){
        List<TJ> tjListExcepted = new ArrayList<>();
        tjListExcepted.add(tj1);
        tjListExcepted.add(tj2);

        Mockito.when(tjService.findAll()).thenReturn(tjListExcepted);
        Mockito.when(projectService.findById(1L)).thenReturn(project);

        Set<Person> persons = tjListExcepted.stream().map(tj -> tj.getPerson()).collect(Collectors.toSet());
        DatasetDto datasetExcepted= new DatasetDto();

        datasetExcepted.setPersons(personConverter.entityListToDtoList(persons));
        datasetExcepted.setProject(projectConverter.entityToDto(project));

        DatasetDto datasetEntry=dataSetService.getByProject(1L);
        assertEquals(datasetExcepted, datasetEntry);
        Mockito.verify(tjService, Mockito.times(1)).findAll();
        Mockito.verify(projectService, Mockito.times(1)).findById(1L);
    }
}

