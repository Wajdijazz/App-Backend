package com.followup.davidson.services.implementation;

import com.followup.davidson.model.Dataset;
import com.followup.davidson.model.PersonDto;
import com.followup.davidson.model.ProjectDto;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IDatasetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DataSetServiceImp implements IDatasetService {
    private TJRepository tjRepository;

    public DataSetServiceImp(TJRepository tjRepository) {
        this.tjRepository = tjRepository;
    }

    /**
     * Cette methode permet de creer un object contient tous les personnes pour chaque projet
     *
     * @param projectId
     * @return
     */
    @Override
    public Dataset getByProject(Long projectId) {
        List<TJ> tjs = tjRepository.findAll();
        List<TJ> tjsProject = tjRepository.findByProject(projectId);
        Dataset dataset = new Dataset();
        List<PersonDto> personDtos = new ArrayList<>();
        tjs.forEach(tj -> {
            PersonDto personDto = PersonDto.builder()
                    .personId(tj.getPerson().getPersonId())
                    .firstName(tj.getPerson().getFirstName())
                    .lastName(tj.getPerson().getLastName())
                    .build();
            if (!personDtos.contains(personDto)) {
                personDtos.add(personDto);
            }
            dataset.setPersons(personDtos);
            tjsProject.forEach(tjproject -> {
                ProjectDto projectDto = ProjectDto.builder()
                        .project(tjproject.getProject().getProjectName())
                        .build();
                dataset.setProject(projectDto);
            });
        });
        return dataset;
    }
}
