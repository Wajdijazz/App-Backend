package com.followup.davidson.services;

import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Person;

import java.util.Collection;
import java.util.List;

public interface IPersonService {

    List<PersonDto> findAll();
    PersonDto createOrUpdate(PersonDto personDto, Long managerId);

    PersonDto updateIsActiveByPersonId(Long personId, Boolean isActive);

    PersonDto findById(Long id);

    void deletePerson(Long personId);

    List<PersonDto> findActivePersons();

}
