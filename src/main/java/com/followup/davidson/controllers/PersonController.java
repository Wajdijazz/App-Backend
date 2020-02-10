package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Person;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IPersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Routes.PERSON)
@CrossOrigin(origins = "*")
public class PersonController {
    private IPersonService personService;

    private IManagerService managerService;

    public PersonController(IPersonService personService, IManagerService managerService) {
        this.personService = personService;
        this.managerService = managerService;
    }

    @GetMapping(value = "/", produces = {"application/json"})
    public List<Person> getAllPerson() {
        return personService.findAll();
    }


    @PostMapping("/manager/{managerId}/person")
    public Person createPerson(@Valid @RequestBody Person person, @PathVariable(value = "managerId") Long managerId) {
        return personService.create(person, managerId);
    }
    @PutMapping("/{personId}/{managerId}")
    public Person updatePerson( @PathVariable(value = "personId") Long personId,@Valid @RequestBody Person person,
                                @PathVariable(value = "managerId") Long managerId) {
        return personService.updatePerson(personId,person, managerId);
    }
    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable(value = "id") Long personId) {
        return personService.findById(personId);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(value = "id") Long personId) {
        personService.deletePerson(personId);
    }

}
