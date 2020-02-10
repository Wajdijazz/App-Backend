package com.followup.davidson.services.implementation;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.IClientService;
import com.followup.davidson.services.IProjectService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProjectServiceImpl implements IProjectService {


    private ProjectRepository projectRepository;
    private IClientService clientService;


    public ProjectServiceImpl(ProjectRepository projectRepository, IClientService clientService) {
        this.projectRepository = projectRepository;
        this.clientService = clientService;
    }

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
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    /**
     * Cette methode permet de créer et sauvgarder un nouveau projet
     *
     * @param project
     * @return projet crée
     */
    @Override
    public Project create(Project project, Long clientId) {
        Optional<Client> client = clientService.findById(clientId);
        project.setClient(client.get());
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long projectId, Project project, Long clientId) {
        Optional<Client> client=clientService.findById(clientId);
        Project projectUp=new Project().builder()
                .projectId(projectId)
                .projectName(project.getProjectName())
                .client(client.get())
                .build();
        projectRepository.save(projectUp);
        return projectUp;
    }

    /**
     * Cette methode permet de supprimer un projet par son id
     *
     * @param id
     */
    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteInterventionByIdProject(id);
        projectRepository.deleteById(id);
    }
}
