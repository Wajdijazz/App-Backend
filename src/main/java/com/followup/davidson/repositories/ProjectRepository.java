package com.followup.davidson.repositories;
import com.followup.davidson.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project,Long> {

    void deleteByManager_ManagerId(Long managerId);

    Project findByProjectIdAndIsActiveTrue(Long projectId);

}
