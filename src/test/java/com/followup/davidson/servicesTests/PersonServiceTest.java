package com.followup.davidson.servicesTests;


import com.followup.davidson.Utils.DataForTest;
import com.followup.davidson.Utils.Utils;
import com.followup.davidson.controllers.ManagerController;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;

import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.implementation.ManagerServiceImpl;
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
public class PersonServiceTest {
    @Autowired
    private Utils utils;


    private ManagerDto MANAGER_DTO = utils.getManagerDto(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);

    private Manager MANAGER = utils.getManager(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);

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
            DataForTest.PersonData.PERSON_2_ISACTIVE
    );

    private Person PERSON_1 = utils.getPerson(
            DataForTest.PersonData.PERSON_1_ID,
            DataForTest.PersonData.PERSON_1_FIRSTNAME,
            DataForTest.PersonData.PERSON_1_LASTTNAME,
            MANAGER,
            DataForTest.PersonData.PERSON_1_ISACTIVE
    );

    private Person PERSON_2 = utils.getPerson(
            DataForTest.PersonData.PERSON_2_ID,
            DataForTest.PersonData.PERSON_2_FIRSTNAME,
            DataForTest.PersonData.PERSON_2_LASTTNAME,
            MANAGER,
            DataForTest.PersonData.PERSON_2_ISACTIVE
    );


    @MockBean
    private PersonRepository personRepository;

    @SpyBean
    @Autowired
    private IPersonService personService;

    @MockBean
    private IManagerService managerService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(personService.findAll().size(), is(0));
        Mockito.verify(personRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {
        List<Person> personListExpected = Arrays.asList(PERSON_1, PERSON_2);
        Mockito.when(personRepository.findAll()).thenReturn(personListExpected);

        List<PersonDto> personListDtoExpected = Arrays.asList(PERSON_DTO_1, PERSON_DTO_2);
        List<PersonDto> personDtoList = personService.findAll();
        assertEquals(personDtoList, personListDtoExpected);
    }

    @Test
    public void findById() {
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(PERSON_1));
        PersonDto personDtoExcepted = personService.findById(1L);
        assertEquals(personDtoExcepted, PERSON_DTO_1);
    }


    @Test
    void testCreateOrUpdatePerson() {

        Mockito.when(managerService.findById(1L)).thenReturn(MANAGER_DTO);
        Mockito.when(personRepository.save(PERSON_1)).thenReturn(PERSON_1);

        PersonDto personDtoReturned = personService.createOrUpdate(PERSON_DTO_1);

        assertEquals(PERSON_DTO_1, personDtoReturned);
    }

    @Test
    void updateIsActiveByPersonIdTest() {
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(PERSON_1));

        Mockito.when(personRepository.save(PERSON_1)).thenReturn(PERSON_1);

        PersonDto personDtoReturned = personService
                .updateIsActiveByPersonId(PERSON_1.getPersonId(), PERSON_1.isActive());

        assertEquals(PERSON_DTO_1, personDtoReturned);

    }
}

