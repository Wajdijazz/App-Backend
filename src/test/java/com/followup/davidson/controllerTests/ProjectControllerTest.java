package com.followup.davidson.controllerTests;


import com.followup.davidson.controllers.ProjectController;

import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.services.IProjectService;
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
public class ProjectControllerTest {
    private static Project p1;
    private static Project p2;

    @Mock
    private IProjectService projectService;
    @InjectMocks
    private ProjectController projectController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        p1 = new Project(1L, "Followup", null);
        p2 = new Project(1L, "ERP", null);

    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(projectService.findAll()).thenReturn(Arrays.asList());
        assertThat(projectController.getAllProject().size(), is(0));
        Mockito.verify(projectService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {
        Mockito.when(projectService.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(projectController.getAllProject().size(), is(2));
        Mockito.verify(projectService, Mockito.times(1)).findAll();
    }

    @Test
    void findById_WhenMatch() {
        Mockito.when(projectService.findById(1L)).thenReturn(Optional.of(p1));
        Optional<Project> p = projectController.findProjectById(1L);
        assertThat(p.get(), is(p1));
    }


    @Test
    void deleteById_WhenFound() {
        lenient().when(projectService.findById(1L)).thenReturn(Optional.of(p1));
        projectController.deletePeroject(1L);
        Mockito.verify(projectService, Mockito.times(1)).deleteProject(1L);
    }

    @Test
    void createOnClickAddProject() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectService.create(Mockito.any(Project.class), anyLong())).thenReturn(p1);
        ResponseEntity<Project> responseEntity = projectController.createProject(p1, 1L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
    @Test
    void updateOnClickUpdateProject() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(projectService.updateProject(anyLong(),Mockito.any(Project.class), anyLong())).thenReturn(p1);
        ResponseEntity<Project> responseEntity = projectController.updateProject(1L,p1, 1L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }


}
