package com.followup.davidson.controllerTests;


import com.followup.davidson.controllers.TJController;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.ITJService;
import org.assertj.core.api.Assertions;
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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjControllerTest {
    private static TJ tj1;
    private static TJ tj2;
    @MockBean
    private ITJService tjService;

    @Autowired
    private TJController tjController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
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
    void findAll_whenNoRecord() {

        Mockito.when(tjService.findAll()).thenReturn(Arrays.asList());
        assertThat(tjController.getAllTj().size(), is(0));
        Mockito.verify(tjService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {

        Mockito.when(tjService.findAll()).thenReturn(Arrays.asList(tj1, tj2));
        assertThat(tjController.getAllTj().size(), is(2));
        Mockito.verify(tjService, Mockito.times(1)).findAll();
    }

    @Test
    void findById_WhenMatch() {
        Mockito.when(tjService.findById(1L)).thenReturn(tj1);
        TJ tj = tjController.findTjById(1L);
        assertThat(tj, is(tj1));
    }


    @Test
    void deleteById_WhenFound() {
        lenient().when(tjService.findById(1L)).thenReturn(tj1);
        tjController.deleteTj(1L);
        Mockito.verify(tjService, Mockito.times(1)).deleteTj(1L);
    }

    @Test
    void createTjTest() {
        TjDto tjEntry = getTjDto();
        //expected
        TjDto tjExpceted = getTjDto();
        //Mocks
        Mockito.when(tjService.create(tjEntry))
                .thenReturn(tjExpceted);
        //call
        TjDto effective = tjController.createTj(tjEntry);
        //Asset
        assertEquals(tjExpceted, effective);
        Mockito.verify(tjService, Mockito.times(1)).create(tjEntry);
    }

    @Test
    void updateTjTest(){

        TjDto tjEntry = getTjDto();
        //expected
        TjDto tjExpceted = getTjDto();
        //Mocks
        Mockito.when(tjService.updateByProjectAndPerson(tjEntry))
                .thenReturn(tjExpceted);
        //call
        TjDto effective = tjController.updateTj(tjEntry);
        //Asset
        assertEquals(tjExpceted, effective);
        Mockito.verify(tjService, Mockito.times(1)).updateByProjectAndPerson(tjEntry);
    }

}
