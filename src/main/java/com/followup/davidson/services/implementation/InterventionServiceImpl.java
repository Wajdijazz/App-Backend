package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.InterventionConverter;
import com.followup.davidson.dto.InterventionDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.Mode;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.InterventionRepository;
import com.followup.davidson.services.IInterventionService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class InterventionServiceImpl implements IInterventionService {

    InterventionRepository interventionRepository;
    private IProjectService projectService;
    private IPersonService personService;

    private InterventionConverter interventionConverter;

    /**
     * Cette methode permet de retourner une intervention par id
     *
     * @param id
     * @return une intervention
     */
    @Override
    public Intervention findById(Long id) {
        return interventionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("This intervention with Id" + id + "not exist"));
    }

    /**
     * Cette methode permet de lister tous les interventions
     *
     * @return une liste des {@link Intervention}
     */
    @Override
    public List<Intervention> findAll() {
        return interventionRepository.findAll();
    }


    public Map<Date, List<Intervention>> findAllByDay() {
        List<Intervention> interventions = interventionRepository.findAll();

        return interventions.stream().collect(
                Collectors.groupingBy(
                        Intervention::getDate, Collectors.toList()
                )
        );

    }

    /**
     * cette methode permet de sauvgarder des interventions d'une personne sur un projet
     * elle prend en parametre une liste d'object InterventionDto
     *
     * @param interventionDtos
     */
    public List<InterventionDto> saveInterventions(List<InterventionDto> interventionDtos) {

        List<Intervention> listInt = interventionDtos.stream()
                .map(interventionDto -> interventionConverter.dtoToEntity(interventionDto))
                .collect(Collectors.toList());

        interventionRepository.saveAll(listInt);
        return interventionDtos;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }

    /**
     * Cette methode permet de supprimer tous les interventions par l'id de personne et l'id de projet au méme temps
     *
     * @param personId
     * @param projectId
     */
    @Override
    public void deleteIntervention(Long personId, Long projectId) {
        interventionRepository.deleteIntervention(personId, projectId);
    }

    /**
     * Cette methode permet de supprimer une intervention par id intervention
     *
     * @param id
     */
    @Override
    public void deleteInterventionHistorique(Long id) {
        interventionRepository.deleteById(id);
    }

    /**
     * Cette methode permet de trouver tous les interventions par l'id de personne et l'id de projet au méme temps
     *
     * @param projectId
     * @param personId
     * @return
     */
    @Override
    public List<Intervention> findByPersonAndProject(long projectId, long personId) {
        return interventionRepository.findByPersonAndProject(projectId, personId);
    }

    /**
     * cette methode permet de compter le nombre des jours travaillés d'une personne sur un projet
     *
     * @param projectId
     * @param personId
     * @return un entier
     */
    @Override
    public Float workedDayByPersonAndProject(long projectId, long personId) {
        return interventionRepository.workedDayByPersonAndProject(projectId, personId) / 2;
    }

    @Override
    public Float workedDayByPersonAndProjectByMonth(long projectId, long personId, long monthNumber, long yearNumber) {
        return interventionRepository.workedDayByPersonAndProjectAndMonthAndYear(projectId, personId, monthNumber, yearNumber) / 2;
    }
}
