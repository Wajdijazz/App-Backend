package com.followup.davidson.services;

import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.model.Person;

import java.util.List;

public interface IPersonService {

    List<Person> findAll();

    PersonDto createOrUpdate(PersonDto personDto);

    Person findById(Long id);

    void deletePerson(Long personId);
}
