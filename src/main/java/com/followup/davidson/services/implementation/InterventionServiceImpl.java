package com.followup.davidson.services.implementation;

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

@Transactional
@AllArgsConstructor
@Service
public class InterventionServiceImpl implements IInterventionService {

    InterventionRepository interventionRepository;
    private IProjectService projectService;
    private IPersonService personService;

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

    /**
     * cette methode permet de sauvgarder lhistorique des interventions d'une personne sur un projet par details , les weekend sont
     * automatiquement eliminés
     * elle prend en paramatre , date de debut des interventions et date de fins des interventions
     *
     * @param interventionDto
     * @param personId
     * @param projectId
     */
    @Override
    public Object saveInterventions(InterventionDto interventionDto, Long personId, Long projectId) {
    /*    Project project = projectService.findById(projectId);
        Person person = personService.findById(personId);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(interventionDto.getStartDate());
        cal2.setTime(interventionDto.getEndDate());
        while (cal1.compareTo(cal2) <= 0) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                Date date = cal1.getTime();
                java.sql.Date sDate = convertUtilToSql(date);

                Intervention intervention1 = Intervention.builder()
                        .person(person)
                        .project(project)
                        .date(sDate)
                        .mode(Mode.AM)
                        .build();

                Intervention intervention2 = Intervention.builder()
                        .person(person)
                        .project(project)
                        .date(sDate)
                        .mode(Mode.PM)
                        .build();

                interventionRepository.save(intervention1);
                interventionRepository.save(intervention2);
            }
            cal1.add(Calendar.DATE, 1);
        }*/
        return interventionDto;
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
