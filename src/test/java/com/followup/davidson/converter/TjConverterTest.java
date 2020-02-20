package com.followup.davidson.converter;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.implementation.PersonServiceImpl;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjConverterTest {
 /*   private static Person person;
    private static Project project;

    @SpyBean
    @Autowired
    private TjConverter tjConverter;

    @MockBean
    private PersonServiceImpl personService;

    @MockBean
    private ProjectServiceImpl projectService;

    @BeforeAll
    public static void init() {
        person = new Person(1L, "Wajdi", "Jaziri", null);
     //   project = new Project(1L, "Followup", null);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dtoToEntityTest() {
        Mockito.when(personService.findById(1L)).thenReturn(person);
        Mockito.when(projectService.findById(1L)).thenReturn(project);
        //Entry
        TjDto entry = createDtoTj(1L);
        //Excepted
        TJ expected = createEntityTj(1L);
        //call
        TJ effective = tjConverter.dtoToEntity(entry);
        //Asset
        assertEquals(expected, effective);
        Mockito.verify(personService, Mockito.times(1)).findById(1L);
        Mockito.verify(projectService, Mockito.times(1)).findById(1L);
    }

    @Test
    public void entityToDtoTest() {
        //Entry
        TJ entry = createEntityTj(1L);
        //Excepted
        TjDto expected = createDtoTj(1L);
        //call
        TjDto effective = tjConverter.entityToDto(entry);
        //Asset
        assertEquals(expected, effective);
    }

    private TjDto createDtoTj(Long id) {
        return TjDto.builder()
                .tjId(id)
                .tarif(500f)
                .personId(1L)
                .projectId(1L)
                .build();
    }

    private TJ createEntityTj(Long id) {
        return TJ.builder()
                .tjId(id)
                .tarif(500f)
                .person(person)
                .project(project)
                .build();
    }*/
}
