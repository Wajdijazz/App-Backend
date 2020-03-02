package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.converter.TjConverter;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IDashboardService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class TJServiceImpl implements ITJService {
    private TJRepository tjRepository;
    private TjConverter tjConverter;
    private IProjectService projectService;
    private IPersonService personService;
    private IDashboardService dashboardService;


    @Override
    public TjDto create(TjDto tjDto, Long projectId, Long personId) {

        tjDto.setProjectDto(projectService.findById(projectId));
        tjDto.setPersonDto(personService.findById(personId));

      //  dashboardService.createDashboard(projectId, personId, tjDto.getTarif());

        TJ tj = tjRepository.findByProject_ProjectIdAndPerson_PersonId(projectId, personId);
        if (tj == null) {
            return tjConverter.entityToDto(tjRepository.save(tjConverter.dtoToEntity(tjDto)));
        } else if (tj.getTarif() == 0) {
            return updateByProjectAndPerson(tjDto, projectId, personId);
        } else {
            return tjConverter.entityToDto(tjRepository.save(tjConverter.dtoToEntity(tjDto)));
        }
    }

    @Override
    public void deleteTjByPerson(Long personId) {
        personService.deletePerson(personId);
    }

    @Override
    public void deleteTjByProject(Long projectId) {
        projectService.deleteProject(projectId);
    }

    /**
     * Cette methode permet de modifin un tj par projectId et personId
     *
     * @param tjDto
     * @return
     */
    @Override
    public TjDto updateByProjectAndPerson(TjDto tjDto, Long projectId, Long personId) {
        tjDto.setProjectDto(projectService.findById(projectId));
        tjDto.setPersonDto(personService.findById(personId));

        TJ tjByProjectAndPerson = tjRepository.findByProject_ProjectIdAndPerson_PersonId(projectId, personId);
        tjDto.setTjId(tjByProjectAndPerson.getTjId());

        return tjConverter.entityToDto(tjRepository.save(tjConverter.dtoToEntity(tjDto)));
    }

    /**
     * Cette methode permet de trouver le tarif par project et personne
     *
     * @param projectId
     * @param personId
     * @return un tarif de type Long
     */
    @Override
    public Float findTarifByProject_ProjectIdAndPerson_PersonId(Long projectId, Long personId) {
        if (projectId == null || personId == null) {
            return null;
        } else {
            TJ tj = tjRepository.findByProject_ProjectIdAndPerson_PersonId(projectId, personId);
            if (tj == null) {
                return null;
            }
            return tj.getTarif();
        }
    }

}
