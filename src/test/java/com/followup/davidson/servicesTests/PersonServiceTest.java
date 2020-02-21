package com.followup.davidson.servicesTests;



import com.followup.davidson.controllers.ManagerController;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;

import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.implementation.PersonServiceImpl;
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
public class PersonServiceTest {
private static Person p1;
private static Person p2;
private static Manager m1;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks

    private PersonServiceImpl personService;

    @Mock
    private IManagerService managerService;

    @InjectMocks
    private ManagerController managerController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        p1=new Person(1L,"Wajdi","Jaziri",null);
        p2=new Person(1L,"Davidson","Consulting",null);
        m1=new Manager(1L,"Wajdi","Jaziri");



    }
    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(personService.findAll().size(), is(0));
        Mockito.verify(personRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {
        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(personService.findAll().size(), is(2));
        assertThat(personService.findAll().get(0), is(p1));
        assertThat(personService.findAll().get(1),is(p2));
        Mockito.verify(personRepository, Mockito.times(3)).findAll();

    }

    @Test
    public void findById() {
 /*       Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(p1));
        assertThat(personService.findById(1L), is(Optional.of(p1)));
        Mockito.verify(personRepository, Mockito.times(1)).findById(1L);*/
    }

    @Test
    void deleteById() {
    /*    personService.deletePerson(1L);
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(1L);*/

    }

    @Test
    void create() {
    /*   Mockito.when(managerService.findById(1L)).thenReturn(m1);
        Manager m = managerController.findManagerById(1L);
        assertThat(m, is(m1) );

        Mockito.when(personRepository.save(p1)).thenReturn(p1);
        assertThat(personService.create(p1), is(p1));
        Mockito.verify(personRepository, Mockito.times(1)).save(p1);

        Mockito.when(personRepository.save(p2)).thenReturn(p2);
        assertThat(personService.create(p2,1L), is(p2));
        Mockito.verify(personRepository, Mockito.times(1)).save(p2);*/
    }
}

