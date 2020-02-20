package com.followup.davidson.repositories;
import com.followup.davidson.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project,Long> {

    void deleteByManager_ManagerId(Long managerId);

    @Modifying
    @Query(value=" DELETE FROM intervention i WHERE i.project_id=:projectId",nativeQuery = true)
    void deleteInterventionByIdProject( @Param("projectId") Long projectId);

}
