package com.followup.davidson.repositories;

import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.TJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface TJRepository extends JpaRepository<TJ,Long> {



    List<TJ> findByProject_ProjectId(long projectId);

    TJ  findByProject_ProjectIdAndPerson_PersonId(Long projectId, Long personId);

    void deleteByProject_ProjectId(Long projectId);

    void deleteByPerson_PersonId(Long personId);



}
