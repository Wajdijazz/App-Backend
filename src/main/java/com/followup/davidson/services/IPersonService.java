package com.followup.davidson.services;

import com.followup.davidson.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<Person> findAll();
    Person create(Person person);
    Optional<Person> findById(Long id);
    void deletePerson(Long id);
}
