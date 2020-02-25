package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.converter.ProjectConverter;
import com.followup.davidson.converter.TjConverter;
import com.followup.davidson.dto.TjDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
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
    private ProjectConverter projectConverter;
    private PersonConverter personConverter;
    private IProjectService projectService;
    private IPersonService personService;





    @Override
    public TjDto create(TjDto tjDto) {
        tjDto.setProjectDto(projectService.findById(tjDto.getProjectId()));
        tjDto.setPersonDto(personService.findById(tjDto.getPersonId()));

        return tjConverter.entityToDto(tjRepository.save(tjConverter.dtoToEntity(tjDto)));
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
     * Cette methode permet de modifin un tj par projectId et personId
     *
     * @param tjDto
     * @return
     */
    @Override
    public TjDto updateByProjectAndPerson(TjDto tjDto) {
        tjDto.setProjectDto(projectService.findById(tjDto.getProjectId()));
        tjDto.setPersonDto(personService.findById(tjDto.getPersonId()));

        TJ tjByProjectAndPerson = tjRepository.findByProject_ProjectIdAndPerson_PersonId(tjDto.getProjectId(), tjDto.getPersonId());
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
        TJ tj = tjRepository.findByProject_ProjectIdAndPerson_PersonId(projectId, personId);
        if (projectId == null || personId == null || tj == null) {
            return null;
        } else {
            return tj.getTarif();
        }
    }



}
