package com.followup.davidson.repositories;
import com.followup.davidson.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface PersonRepository  extends JpaRepository<Person,Long> {

    @Modifying
    @Query(value=" DELETE FROM intervention i WHERE i.person_id=:personId",nativeQuery = true)
    void deleteInterventionByIdPerson( @Param("personId") Long personId);


}
