package com.followup.davidson.servicesTests;


import com.followup.davidson.model.Manager;
import com.followup.davidson.repositories.ManagerRepository;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ManagerServiceTest {
    private static Manager m1;
    private static Manager m2;

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks

    private ManagerServiceImpl managerService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        m1=new Manager(1L,"Wajdi","Jaziri");
        m2=new Manager(2L,"test","test");
    }
    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(managerRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(managerService.findAll().size(), is(0));
        Mockito.verify(managerRepository, Mockito.times(1)).findAll();

    }

   @Test
    public void findAllTest_WhenRecord() {
    /*    Mockito.when(managerRepository.findAll()).thenReturn(Arrays.asList(m1, m2));
        assertThat(managerService.findAll().size(), is(2));
        assertThat(managerService.findAll().get(0), is(m1));
        assertThat(managerService.findAll().get(1),is(m2));
        Mockito.verify(managerRepository, Mockito.times(3)).findAll();
*/
    }
    @Test
    void create() {
/*
        Mockito.when(managerRepository.save(m1)).thenReturn(m1);
        assertThat(managerService.create(m1), is(m1));
        Mockito.verify(managerRepository, Mockito.times(1)).save(m1);

        Mockito.when(managerRepository.save(m2)).thenReturn(m2);
        assertThat(managerService.create(m2), is(m2));
        Mockito.verify(managerRepository, Mockito.times(1)).save(m2);*/
    }

    @Test
    public void findById() {
/*
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.of(m1));
        assertThat(managerService.findById(1L), is(Optional.of(m1)));
        Mockito.verify(managerRepository, Mockito.times(1)).findById(1L);*/
    }

    @Test
    void deleteById() {
     /**   managerService.deleteManager(1L);
        Mockito.verify(managerRepository, Mockito.times(1)).deleteById(1L);*/
    }
}
