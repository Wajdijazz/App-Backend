package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.ProjectRepository;

import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class ProjectServiceImpl implements IProjectService {


    private ProjectRepository projectRepository;
    private ProjectConverter projectConverter;
    private TJRepository tjRepository;


    /**
     * Cette methode permet de lister tous les projets de davidsons
     *
     * @return une liste des {@link Project}
     */
    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /**
     * Cette methode permet de retourner un projet par id
     *
     * @param id
     * @return un client
     */
    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("This project with Id" + id + "not exist"));
    }


    @Override
    public ProjectDto createOrUpdate(ProjectDto projectDto) {
        return projectConverter.entityToDto(projectRepository.save(projectConverter.dtoToEntity(projectDto)));
    }


    /**
     * Cette methode permet de supprimer un projet par son id
     *
     * @param id
     */
    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteInterventionByIdProject(id);
        tjRepository.deleteByProject_ProjectId(id);
        projectRepository.deleteById(id);
    }
}
