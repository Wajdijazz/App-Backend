package com.followup.davidson.repositories;

import com.followup.davidson.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DashboardRepository extends JpaRepository<Dashboard,Long> {
}
