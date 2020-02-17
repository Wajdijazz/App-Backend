package com.followup.davidson.servicesTests;

import com.followup.davidson.controllers.PersonController;
import com.followup.davidson.controllers.ProjectController;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjServiceTest {
    private static TJ tj1;
    private static TJ tj2;
    private static Project p1;
    private static Person pe1;

    @Mock
    private TJRepository tjRepository;

    @InjectMocks

    private TJServiceImpl tjService;

    @Mock

    private ProjectServiceImpl projectService;

    @InjectMocks
    private ProjectController projectController;

    @Mock

    private PersonServiceImpl personService;

    @InjectMocks
    private PersonController personController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        tj1=new TJ(1L,50f,null,null);
        tj2=new TJ(1L,70f,null,null);
        p1=new Project(1L,"Followup",null);
        pe1=new Person(1L,"Wajdi","Jaziri",null);


    }
    @Test
    public void findAllTest_WhenNoRecord() {
        Mockito.when(tjRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(tjService.findAll().size(), is(0));
        Mockito.verify(tjRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findAllTest_WhenRecord() {
        Mockito.when(tjRepository.findAll()).thenReturn(Arrays.asList(tj1, tj2));
        assertThat(tjService.findAll().size(), is(2));
        assertThat(tjService.findAll().get(0), is(tj1));
        assertThat(tjService.findAll().get(1),is(tj2));
        Mockito.verify(tjRepository, Mockito.times(3)).findAll();
    }

    @Test
    public void findById() {
        Mockito.when(tjRepository.findById(1L)).thenReturn(Optional.of(tj1));
        assertThat(tjService.findById(1L), is(Optional.of(tj1)));
        Mockito.verify(tjRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        tjService.deleteTj(1L);
        Mockito.verify(tjRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void create() {
        Mockito.when(projectService.findById(1L)).thenReturn(p1);
        Project p = projectController.findProjectById(1L);
        assertThat(p, is(p1) );

        Mockito.when(personService.findById(1L)).thenReturn(pe1);
        Person pe = personController.findPersonById(1L);
        assertThat(pe, is(pe1) );

        Mockito.when(tjRepository.save(tj1)).thenReturn(tj1);
        assertThat(tjService.create(tj1,1L,1L), is(tj1));
        Mockito.verify(tjRepository, Mockito.times(1)).save(tj1);

        Mockito.when(tjRepository.save(tj2)).thenReturn(tj2);
        assertThat(tjService.create(tj2,1L,1L), is(tj2));
        Mockito.verify(tjRepository, Mockito.times(1)).save(tj2);
    }
}
