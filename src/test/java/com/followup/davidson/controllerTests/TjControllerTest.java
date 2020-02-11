package com.followup.davidson.controllerTests;


import com.followup.davidson.controllers.TJController;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TjControllerTest {
    private static TJ tj1;
    private static TJ tj2;
    @Mock
    private ITJService tjService;
    @InjectMocks
    private TJController tjController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        tj1 = new TJ(1L, 50, null, null);
        tj2 = new TJ(1L, 70, null, null);
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

        Mockito.when(tjService.findById(1L)).thenReturn(Optional.of(tj1));
        Optional<TJ> tj = tjController.findTjById(1L);
        assertThat(tj.get(), is(tj1));
    }


    @Test
    void deleteById_WhenFound() {
        lenient().when(tjService.findById(1L)).thenReturn(Optional.of(tj1));
        tjController.deleteTj(1L);
        Mockito.verify(tjService, Mockito.times(1)).deleteTj(1L);
    }

    @Test
    void createOnClickAddTj() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(tjService.create(Mockito.any(TJ.class), anyLong(), anyLong())).thenReturn(tj1);
        ResponseEntity<TJ> responseEntity = tjController.createTj(tj1, 1L, 1L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    void updateOnClickUpdateTj() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(tjService.updateTj(anyLong(),Mockito.any(TJ.class), anyLong(), anyLong())).thenReturn(tj1);
        ResponseEntity<TJ> responseEntity = tjController.updateTj(tj1,1L, 1L, 1L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

}
