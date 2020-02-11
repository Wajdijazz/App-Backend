package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, @PathVariable(value = "managerId") Long managerId) {
        personService.create(person, managerId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{personId}")
                .buildAndExpand(person.getPersonId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{personId}/{managerId}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "personId") Long personId, @Valid @RequestBody Person person,
                               @PathVariable(value = "managerId") Long managerId) {
        personService.updatePerson(personId,person,managerId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{personId}")
                .buildAndExpand(person.getPersonId())
                .toUri();
        return ResponseEntity.created(location).build();
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
