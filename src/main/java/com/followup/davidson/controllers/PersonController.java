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

    @GetMapping(value = "/", produces = {"application/json"})
    public Collection<PersonDto> getAllPerson() {
        return personService.findAll();
    }

    @PostMapping("/")
    public PersonDto createuPerson(@Valid @RequestBody PersonDto personDto) {
        return personService.createOrUpdate(personDto);
    }

    @PutMapping("/")
    public PersonDto updatePerson(@Valid @RequestBody PersonDto personDto) {
        return personService.createOrUpdate(personDto);
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
