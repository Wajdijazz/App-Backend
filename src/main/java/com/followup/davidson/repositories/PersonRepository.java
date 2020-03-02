package com.followup.davidson.repositories;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface PersonRepository  extends JpaRepository<Person,Long> {

    void deleteByManager_ManagerId(Long managerId);

    @Modifying
    @Query(value=" DELETE FROM intervention i WHERE i.person_id=:personId",nativeQuery = true)
    void deleteInterventionByIdPerson( @Param("personId") Long personId);

    List<Person> findByIsActiveTrue();




}
