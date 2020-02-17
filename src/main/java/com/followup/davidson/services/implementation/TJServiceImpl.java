package com.followup.davidson.services.implementation;

import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class TJServiceImpl implements ITJService {
    private TJRepository tjRepository;
    private IProjectService projectService;
    private IPersonService personService;

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
     * Cette methode permet de verifier d'abord si le tj existe par person adn project
     * et de faire update si existe ou bien de creér un nouveau tj si n'existe pas
     *
     * @param tj
     * @param projectId
     * @param personId
     * @return le TJ crée
     */
    @Override
    public TJ create(TJ tj, Long projectId, Long personId) {
        TJ TjIfExist = tjRepository.findByPersonAndProject(projectId, personId);
        Project project = projectService.findById(projectId);
        Person person = personService.findById(personId);
        if (TjIfExist != null) {
            TJ tjUpdateByProjectAndPerson = new TJ().builder()
                    .tjId(TjIfExist.getTjId())
                    .tarif(tj.getTarif())
                    .project(project)
                    .person(person)
                    .build();
            tjRepository.save(tjUpdateByProjectAndPerson);
            return tjUpdateByProjectAndPerson;
        } else {
            TJ tjCreated = new TJ().builder()
                    .tarif(tj.getTarif())
                    .project(project)
                    .person(person)
                    .build();
            tjRepository.save(tjCreated);
            return tjCreated;
        }
    }

    @Override
    public TJ findById(Long id) {
        return tjRepository.findById(id).orElseThrow(() -> new ApplicationException("This Tj with Id" + id + "not exist"));
    }

    @Override
    public TJ updateTj(Long tjId, TJ tj, Long projectId, Long personId) {
        Project project = projectService.findById(projectId);
       Person person = personService.findById(personId);
        TJ tjUp = new TJ().builder()
                .tjId(tjId)
                .tarif(tj.getTarif())
                .project(project)
                .person(person)
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

    @Override
    public List<TJ> findByProject(long projectId) {
        return tjRepository.findByProject(projectId);
    }

    /**
     * Cette methode permet de trouver le tarif par project et personne
     *
     * @param projectId
     * @param personId
     * @return un tarif de type Long
     */
    @Override
    public Long findTarif(Long projectId, Long personId) {
        if (projectId == null || personId == null) {
            return null;
        } else {
            return tjRepository.findTarif(projectId, personId);
        }
    }

}
