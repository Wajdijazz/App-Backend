package com.followup.davidson.servicesTests;

import com.followup.davidson.controllers.PersonController;
import com.followup.davidson.controllers.ProjectController;
import com.followup.davidson.converter.TjConverter;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.implementation.PersonServiceImpl;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
import com.followup.davidson.services.implementation.TJServiceImpl;
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
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjServiceTest {/*
    private static TJ tj1;
    private static TJ tj2;
    private static Project project;
    private static Person person;

    @Autowired
    private TjConverter tjConverter;

    @MockBean
    private TJRepository tjRepository;

    @SpyBean
    @Autowired
    private TJServiceImpl tjService;

    @MockBean
    private ProjectServiceImpl projectService;

    @MockBean
    private PersonServiceImpl personService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        project = new Project(1L, "Followup", null,null);
        person = new Person(1L, "Wajdi", "Jaziri", null);
        tj1 = new TJ(1L, 50f, null, null);
        tj2 = new TJ(1L, 70f, null, null);

    }

    private TjDto getTjDto() {
        return TjDto.builder()
                .tjId(1L)
                .projectId(1L)
                .personId(1L)
                .tarif(50f)
                .build();
    }

    @Test
    public void findAllTest_WhenNoRecord() {
        Mockito.when(tjRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(tjService.findAll().size(), is(0));
        Mockito.verify(tjRepository, Mockito.times(1)).findAll();

    }
/*
    @Test
    public void findAllTest_WhenRecord() {
        Mockito.when(tjRepository.findAll()).thenReturn(Arrays.asList(tj1, tj2));
        assertThat(tjService.findAll().size(), is(2));
        assertThat(tjService.findAll().get(0), is(tj1));
        assertThat(tjService.findAll().get(1), is(tj2));
        Mockito.verify(tjRepository, Mockito.times(3)).findAll();
    }*/

    @Test
    public void findTjByIdWhenAReponseIsThere() {
    /*    Mockito.when(tjRepository.findById(1L)).thenReturn(Optional.of(tj1));
        TJ tjExcepted = tjService.findById(1L);
        assertEquals(tjExcepted, tj1);
        Mockito.verify(tjRepository, Mockito.times(1)).findById(1L);*
    }

    @Test
    void deleteById() {
        tjService.deleteTj(1L);
        Mockito.verify(tjRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testCreateTjt() {
   /*     TjDto tjDto = getTjDto();
        Mockito.when(personService.findById(1L)).thenReturn(person);
        Mockito.when(projectService.findById(1L)).thenReturn(project);

        TJ tjEntryRepository = tjConverter.dtoToEntity(tjDto);
        TjDto excepted = getTjDto();
        excepted.setTjId(1L);
        excepted.setPersonId(1L);
        excepted.setProjectId(1L);
        excepted.setTarif(50f);

        Mockito.when(tjRepository.save(tjEntryRepository)).thenReturn(tjEntryRepository);
        TjDto tjDtoReturned = tjService.create(tjDto);
        assertEquals(excepted, tjDtoReturned);
        Mockito.verify(personService, Mockito.times(2)).findById(1L);
        Mockito.verify(projectService, Mockito.times(2)).findById(1L);
        Mockito.verify(tjRepository, Mockito.times(1)).save(tjConverter.dtoToEntity(tjDto));
    }

    @Test
    void testUpdateByProjectAndPerson(){
/*
        TjDto tjDto = getTjDto();
        Mockito.when(tjRepository.findByProject_ProjectIdAndPerson_PersonId(1L,1L)).thenReturn(tj1);

        Mockito.when(personService.findById(1L)).thenReturn(person);
        Mockito.when(projectService.findById(1L)).thenReturn(project);

        TJ tjEntryRepository = tjConverter.dtoToEntity(tjDto);
        TjDto excepted = getTjDto();
        excepted.setTjId(1L);
        excepted.setPersonId(1L);
        excepted.setProjectId(1L);
        excepted.setTarif(50f);

        Mockito.when(tjRepository.save(tjEntryRepository)).thenReturn(tjEntryRepository);
        TjDto tjDtoReturned = tjService.create(tjDto);
        assertEquals(excepted, tjDtoReturned);
        Mockito.verify(personService, Mockito.times(2)).findById(1L);
        Mockito.verify(projectService, Mockito.times(2)).findById(1L);
        Mockito.verify(tjRepository, Mockito.times(1)).save(tjConverter.dtoToEntity(tjDto));
*/
    }
}
