package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.model.*;
import com.followup.davidson.services.IDatasetService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class DataSetServiceImp implements IDatasetService {

    private ITJService tjService;
    private IProjectService projectService;
    private ProjectConverter projectConverter;
    private PersonConverter personConverter;

    /**
     * Cette methode permet de creer un object contient tous les personnes pour chaque projet
     *
     * @param projectId
     * @return
     */
    @Override
    public DatasetDto getByProject(Long projectId) {// return builder
        List<TJ> tjs = tjService.findAll(); // TODO, recupérer les Tjs du projet projectId //
        Project project = projectService.findById(projectId);


        Set<Person> persons = tjs.stream().map(tj -> tj.getPerson()).collect(Collectors.toSet());
        DatasetDto dataset =DatasetDto.builder()
                .persons(personConverter.entityListToDtoList(persons))
                .project(projectConverter.entityToDto(project))
                .build();

        return dataset;
    }
}
