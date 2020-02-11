package com.followup.davidson.services.implementation;

import com.followup.davidson.controllers.InterventionController;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.Mode;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.repositories.InterventionRepository;
import com.followup.davidson.services.IInterventionService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class InterventionServiceImpl implements IInterventionService {

    InterventionRepository interventionRepository;
    private IProjectService projectService;
    private IPersonService personService;

    public InterventionServiceImpl(InterventionRepository interventionRepository, IProjectService projectService,
                                   IPersonService personService) {
        this.interventionRepository = interventionRepository;
        this.projectService = projectService;
        this.personService = personService;
    }

    /**
     * Cette methode permet de retourner une intervention par id
     *
     * @param id
     * @return une intervention
     */
    @Override
    public Optional<Intervention> findById(Long id) {
        return interventionRepository.findById(id);
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
     * @param interventionForm
     * @param personId
     * @param projectId
     */
    @Override
    public Object saveInterventions(InterventionController.InterventionForm interventionForm, Long personId, Long projectId) {
        Optional<Project> project = projectService.findById(projectId);
        Optional<Person> person = personService.findById(personId);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(interventionForm.getStartDate());
        cal2.setTime(interventionForm.getEndDate());
        while (cal1.compareTo(cal2) <= 0) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                Date date = cal1.getTime();
                Intervention intervention1 = new Intervention();
                Intervention intervention2 = new Intervention();
                intervention1.setPerson(person.get());
                intervention1.setProject(project.get());
                intervention2.setPerson(person.get());
                intervention2.setProject(project.get());
                java.sql.Date sDate = convertUtilToSql(date);
                intervention1.setDate(sDate);
                intervention2.setDate(sDate);
                intervention1.setMode(Mode.AM);
                intervention2.setMode(Mode.PM);
                interventionRepository.save(intervention1);
                interventionRepository.save(intervention2);
            }
            cal1.add(Calendar.DATE, 1);
        }
        return interventionForm;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
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
    public long workedDayByPersonAndProject(long projectId, long personId) {
        return interventionRepository.workedDayByPersonAndProject(projectId, personId);
    }

    @Override
    public long workedDayByPersonAndProjectByMonth(long projectId, long personId, long monthNumber) {
        return interventionRepository.workedDayByPersonAndProjectInMonth(projectId, personId, monthNumber);
    }
}
