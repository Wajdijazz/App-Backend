package com.followup.davidson.controllerTests;


import com.followup.davidson.controllers.InterventionController;
import com.followup.davidson.converter.InterventionConverter;
import com.followup.davidson.dto.InterventionDto;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.implementation.InterventionServiceImpl;
import com.followup.davidson.services.implementation.PersonServiceImpl;
import com.followup.davidson.services.implementation.ProjectServiceImpl;
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

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.followup.davidson.model.Mode.AM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InterventionControllerTest {
    private static Intervention it1;
    private static Intervention it2;
    private static InterventionDto interventionDto;
    private static Person pe1;
    private static Project p1;
    @Mock
    private InterventionServiceImpl interventionService;
    @InjectMocks
    private InterventionController interventionController;

    private InterventionConverter interventionConverter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        it1 = new Intervention(1L, new Date(2020 - 01 - 01), AM, null, null);
        it2 = new Intervention(2L, new Date(2020 - 01 - 01), AM, null, null);
        interventionDto = new InterventionDto(new Date(2020 - 02 - 03), AM, null, null);
    }

    @Test
    void findAll_whenNoRecord() {
        Mockito.when(interventionService.findAll()).thenReturn(Arrays.asList());
        assertThat(interventionController.getAllIntervention().size(), is(0));
        Mockito.verify(interventionService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {
        Mockito.when(interventionService.findAll()).thenReturn(Arrays.asList(it1, it2));
        assertThat(interventionController.getAllIntervention().size(), is(2));
        Mockito.verify(interventionService, Mockito.times(1)).findAll();
    }

    @Test
    void deleteById1anId2_WhenFound() {
        lenient().when(interventionService.findByPersonAndProject(1L, 1L))
                .thenReturn(Arrays.asList(it1, it2));
        interventionController.deleteInterventionByIdPersonAndProject(1L, 1L);
        Mockito.verify(interventionService, Mockito.times(1))
                .deleteIntervention(1L, 1L);
    }

    @Test
    void deleteById_WhenFound() {
        lenient().when(interventionService.findById(1L)).thenReturn(it1);
        interventionController.deleteInterventionById(1L);
        Mockito.verify(interventionService, Mockito.times(1))
                .deleteInterventionHistorique(1L);
    }
//
//    @Test
//    void createIntervention() {
//        Object intervention = interventionController.createIntervention(interventionDto, 1L, 1L);
//        List<InterventionDto> lInt;
//        lInt.add(interventintervention);
//        Mockito.verify(interventionService, Mockito.times(1))
//                .saveInterventions(lInt,1L,1L);
//  }


}
