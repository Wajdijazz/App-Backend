package com.followup.davidson.servicesTests;


import com.followup.davidson.Utils.DataForTest;
import com.followup.davidson.Utils.Utils;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.implementation.ClientServiceImpl;
import com.followup.davidson.services.implementation.ManagerServiceImpl;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ManagerServiceTest {
    @Autowired
    private Utils utils;

    private ManagerDto MANAGER_DTO_1 = utils.getManagerDto(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);
    private ManagerDto MANAGER_DTO_2 = utils.getManagerDto(
            DataForTest.ManagerData.MANAGER_2_ID,
            DataForTest.ManagerData.MANAGER_2_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_2_LASTTNAME);

    private Manager MANAGER_1 = utils.getManager(
            DataForTest.ManagerData.MANAGER_1_ID,
            DataForTest.ManagerData.MANAGER_1_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_1_LASTTNAME);
    private Manager MANAGER_2 = utils.getManager(
            DataForTest.ManagerData.MANAGER_2_ID,
            DataForTest.ManagerData.MANAGER_2_FIRSTNAME,
            DataForTest.ManagerData.MANAGER_2_LASTTNAME);


    @MockBean
    private ManagerRepository managerRepository;

    @Autowired
    private IManagerService managerService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(managerRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(managerService.findAll().size(), is(0));
        Mockito.verify(managerRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {
        List<Manager> managerListExpected = Arrays.asList(MANAGER_1, MANAGER_2);
        Mockito.when(managerRepository.findAll()).thenReturn(managerListExpected);

        List<ManagerDto> managerListDtoExpected = Arrays.asList(MANAGER_DTO_1, MANAGER_DTO_2);
        List<ManagerDto> managerListDto = managerService.findAll();

        assertEquals(managerListDto, managerListDtoExpected);
    }

    @Test
    void testCreateOrUpdateManager() {
        Mockito.when(managerRepository.save(MANAGER_1)).thenReturn(MANAGER_1);

        ManagerDto managerDtoReturned = managerService.createOrUpdate(MANAGER_DTO_1);

        assertEquals(MANAGER_DTO_1, managerDtoReturned);

    }

    @Test
    public void findManagerByIdWhenAReponseIsThere() {
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.of(MANAGER_1));

        ManagerDto managerExcepted = managerService.findById(1L);

        assertEquals(managerExcepted, MANAGER_DTO_1);
    }

}
