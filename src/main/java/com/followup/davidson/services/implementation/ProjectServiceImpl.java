package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.ClientConverter;
import com.followup.davidson.converter.ManagerConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.*;

import com.followup.davidson.services.IClientService;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class ProjectServiceImpl implements IProjectService {


    private ProjectRepository projectRepository;
    private IManagerService managerService;
    private IClientService clientService;
    private ProjectConverter projectConverter;
    private TJRepository tjRepository;
    private InterventionRepository interventionRepository;


    /**
     * Cette methode permet de lister tous les projets de davidsons
     *
     * @return une liste des {@link Project}
     */
    @Override
    public List<ProjectDto> findAll() {
        return projectConverter.entityListToDtoList(projectRepository.findAll());
    }

    /**
     * Cette methode permet de retourner un projet par id
     *
     * @param id
     * @return un client
     */
    @Override
    public ProjectDto findById(Long id) {
        return projectConverter.entityToDto(projectRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("This project with Id" + id + "not exist")));
    }


    @Override
    public ProjectDto createOrUpdate(ProjectDto projectDto, Long clientId, Long managerId) {
        projectDto.setClientDto(clientService.findById(clientId));
        projectDto.setManagerDto(managerService.findById(managerId));

        return projectConverter.entityToDto(projectRepository.save(projectConverter.dtoToEntity(projectDto)));
    }

    @Override
    public ProjectDto updateIsActiveByProjectId(Long projectId, Boolean isActive) {
        ProjectDto projectDto = findById(projectId);
        projectDto.setActive(isActive);

        return projectConverter.entityToDto(projectRepository.save(projectConverter.dtoToEntity(projectDto)));
    }


    /**
     * Cette methode permet de supprimer un projet par son id
     *
     * @param id
     */
    @Override
    public void deleteProject(Long id) {
        interventionRepository.deleteByProject_ProjectId(id);
        tjRepository.deleteByProject_ProjectId(id);
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDto> findActiveProjects() {
        return projectConverter.entityListToDtoList(projectRepository.findByIsActiveTrue());
    }


}
