package com.followup.davidson.services.implementation;

import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.services.IDatasetService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class DataSetServiceImp implements IDatasetService {

    private IProjectService projectService;
    private IPersonService personService;
    private ITJService tjService;


    /**
     * Cette methode permet de creer un object contient tous les personnes pour chaque projet
     *
     * @param projectId
     * @return
     */
    @Override
    public DatasetDto getByProject(Long projectId) {
        ProjectDto project = projectService.findByProjectIdAndIsActiveTrue(projectId);
        List<PersonDto> persons = personService.findActivePersons();

        DatasetDto dataset = DatasetDto.builder()
                .persons(persons)
                .project(project)
                .build();

        return dataset;
    }

    public DatasetDto getByProjectPersons() {

        List<PersonDto> persons = personService.findActivePersons();
        List<ProjectDto> projects = projectService.findActiveProjects();


        DatasetDto dataset = DatasetDto.builder()
                .persons(persons)
                .projects(projects)
                .build();
        return dataset;
    }
}
