package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.services.IPersonService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Routes.PERSON)
@CrossOrigin(origins = "*")
public class PersonController {
    private IPersonService personService;

    private ManagerRepository managerRepository;

    public PersonController(IPersonService personService,ManagerRepository managerRepository) {
        this.personService=personService;
        this.managerRepository=managerRepository;
    }

    @GetMapping( value = "/", produces = { "application/json" })
    public List<Person> getAllPerson() {
        return personService.findAll();
    }


    @PostMapping("/manager/{managerId}/person")
    public Person createPerson(@Valid @RequestBody Person person, @PathVariable(value = "managerId") Long managerId) {
        return managerRepository.findById(managerId).map(manager -> {
        person.setManager(manager);
        return personService.create(person);
    }).orElseThrow(() -> new ResourceNotFoundException("ManagerId " + managerId + " not found"));

}

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable(value = "id") Long personId)
    {
        return personService.findById(personId);

    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(value = "id") Long personId)
    {
         personService.deletePerson(personId);
    }



}
