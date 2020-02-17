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

    /**
     * cette methode permet de compter le nombre des jours travaillés d'une personne sur un projet
     *
     * @param projectId
     * @param personId
     * @return un entier
     */
    @Query(value = "select count(*) from public.intervention where intervention.project_id= :projectId " +
            "and intervention.person_id= :personId",
            nativeQuery = true)
    Float workedDayByPersonAndProject(long projectId, long personId);

    /**
     * Cette periode permet de calculer les jours de travail "Worked" pour personne et projet pour chaque mois et année
     *
     * @param projectId
     * @param personId
     * @param monthNumber
     * @param yearNumber
     * @return
     */
    @Query(value = "select count(*) from public.intervention WHERE EXTRACT(MONTH FROM date)= :monthNumber " +
            "and EXTRACT(YEAR FROM date)= :yearNumber" +
            " and intervention.project_id= :projectId and intervention.person_id= :personId",
            nativeQuery = true)
    Float workedDayByPersonAndProjectAndMonthAndYear(long projectId, long personId, long monthNumber, long yearNumber);

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

