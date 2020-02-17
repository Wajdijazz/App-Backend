package com.followup.davidson.repositories;

import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.TJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface TJRepository extends JpaRepository<TJ,Long> {

    @Query(value = "select tarif from tj where tj.project_id= :projectId  And tj.person_id= :personId",
            nativeQuery = true)
    Long   findTarif(Long projectId, Long personId);

    @Query(value = "select * from tj where tj.project_id= :projectId",
            nativeQuery = true)
    List<TJ> findByProject(long projectId);

    @Query(value = "select * from tj where tj.project_id= :projectId  And tj.person_id= :personId",
            nativeQuery = true)
    TJ findByPersonAndProject(long projectId, long personId);
}
