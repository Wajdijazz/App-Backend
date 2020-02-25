package com.followup.davidson.services;

import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.model.Person;

import java.util.Collection;
import java.util.List;

public interface IPersonService {

    List<PersonDto> findAll();

    PersonDto createOrUpdate(PersonDto personDto);

    PersonDto findById(Long id);

    void deletePerson(Long personId);
}
