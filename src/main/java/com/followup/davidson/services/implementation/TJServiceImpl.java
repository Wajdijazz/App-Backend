package com.followup.davidson.services.implementation;

import com.followup.davidson.model.Client;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TJServiceImpl implements ITJService {
    private TJRepository tjRepository;
    private IProjectService projectService;
    private IPersonService personService;

    public TJServiceImpl(TJRepository tjRepository, IProjectService projectService, IPersonService personService) {
        this.tjRepository = tjRepository;
        this.projectService = projectService;
        this.personService = personService;
    }

    /**
     * Cette methode permet de lister tous les TJ de chaque personne et pour chaque projet
     *
     * @return une liste des {@link Client}
     */
    @Override
    public List<TJ> findAll() {
        return tjRepository.findAll();
    }

    /**
     * Cette methode permet de créer et sauvgarder un nouveau taus de jour
     *
     * @param tj
     * @param projectId
     * @param personId
     * @return le TJ crée
     */
    @Override
    public TJ create(TJ tj, Long projectId, Long personId) {
        Optional<Project> project = projectService.findById(projectId);
        Optional<Person> person = personService.findById(personId);
        tj.setProject(project.get());
        tj.setPerson(person.get());
        return tjRepository.save(tj);
    }

    /**
     * Cette methode permet de retourner un taux de jour par id
     *
     * @param id
     * @return un TJ
     */
    @Override
    public Optional<TJ> findById(Long id) {
        return tjRepository.findById(id);
    }

    @Override
    public TJ updateTj(Long tjId, TJ tj, Long projectId, Long personId) {
        Optional<Project> project = projectService.findById(projectId);
        Optional<Person> person = personService.findById(personId);
        TJ tjUp = new TJ().builder()
                .tjId(tjId)
                .tarif(tj.getTarif())
                .project(project.get())
                .person(person.get())
                .build();
        tjRepository.save(tjUp);
        return tjUp;
    }

    /**
     * Cette methode permet de supprimer un tj par id
     *
     * @param id
     */
    @Override
    public void deleteTj(Long id) {
        tjRepository.deleteById(id);
    }

    /**
     * Cette methode permet de trouver le tarif par project et personne
     * @param projectId
     * @param personId
     * @return un tarif de type Long
     */
    @Override
    public Long findTarif(Long projectId, Long personId) {
        if(projectId==null | personId==null){
            return null;
        }else {
            return tjRepository.findTarif(projectId, personId);
        }
    }
}
