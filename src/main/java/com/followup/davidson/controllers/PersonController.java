package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.model.Person;
import com.followup.davidson.services.IPersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(Routes.PERSON)
public class PersonController {
    private IPersonService personService;


    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<PersonDto> getAllPerson() {
        return personService.findAll();
    }

    @GetMapping("/active")
    public List<PersonDto> getActivePersons() {
        return personService.findActivePersons();
    }

    @PostMapping("/{managerId}")
    public PersonDto createPerson(@Valid @RequestBody PersonDto personDto, @PathVariable(value = "managerId") Long managerId) {
        return personService.createOrUpdate(personDto, managerId);
    }

    @PutMapping("/manager/{managerId}")
    public PersonDto updatePerson(@Valid @RequestBody PersonDto personDto, @PathVariable(value = "managerId") Long managerId) {
        return personService.createOrUpdate(personDto, managerId);
    }

    @PutMapping("/{personId}")
    public PersonDto updateIsActiveByPersonId(@PathVariable(value = "personId") Long personId, @Valid @RequestBody PersonDto personDto) {
        return personService.updateIsActiveByPersonId(personId, personDto.isActive());
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable(value = "id") Long personId) {
        return personService.findById(personId);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(value = "id") Long personId) {
        personService.deletePerson(personId);
    }

}
