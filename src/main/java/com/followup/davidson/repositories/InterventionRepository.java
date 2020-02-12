package com.followup.davidson.repositories;


import com.followup.davidson.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    /**
     * cette methode permet de lister toutes les interventions enregistrés dans la base de données
     *
     * @return une liste de {@link Intervention}
     */
    @Query(value = "select * from public.intervention ", nativeQuery = true)
    List<Intervention> findAll();

    /**
     * cette methode permet de lister toutes les interventions d'une personne  sur un projet
     *
     * @param projectId : l 'id du projet selectioné
     * @param personId  : l'id du personne selectionnée
     * @return une liste des {@link Intervention}
     */
    @Query(value = "select * from intervention where intervention.project_id= :projectId  And intervention.person_id= :personId",
            nativeQuery = true)
    List<Intervention> findByPersonAndProject(long projectId, long personId);

    //a besoin dans la prochaine sprint
    /**
     * cette methode permet de lister les interventions d'une personne sur un projet dans une periode bien determinée
     *
     * @param person_id  : l'id de la personne selectionée
     * @param project_id : l'id du projet
     * @param firstDate  : la date de debut de la periode
     * @param secondDate : la date de fin de la periode
     * @return une liste des {@link Intervention}
     */
/*    @Query(value = "select count(*) from public.intervention where intervention.person_id= :person_id and intervention.project_id= :project_id and intervention.date between :firstDate AND :secondDate", nativeQuery = true)
    long workedDaysByPeriod(long person_id,long project_id,Date firstDate,Date secondDate);
*/

    /**
     * cette methode permet de compter le nombre des jours travaillés d'une personne sur un projet
     *
     * @param projectId
     * @param personId
     * @return un entier
     */
    @Query(value = "select count(*) from public.intervention where intervention.project_id= :projectId and intervention.person_id= :personId",
            nativeQuery = true)
    long workedDayByPersonAndProject(long projectId, long personId);

    @Query(value = "select count(*) from public.intervention WHERE EXTRACT(MONTH FROM date)= :monthNumber and EXTRACT(YEAR FROM date)= :yearNumber and intervention.project_id= :projectId and intervention.person_id= :personId",
            nativeQuery = true)
    long workedDayByPersonAndProjectInMonthAndYear(long projectId, long personId, long monthNumber,long yearNumber );

    /**
     * cette methode permet de supprimer les interventions par personId et par projectId
     *
     * @param personId
     * @param projectId
     */
    @Modifying
    @Query(value = " DELETE FROM intervention i WHERE i.person_id= :personId AND i.project_id= :projectId",
            nativeQuery = true)
    void deleteIntervention(Long personId, Long projectId);
}

