package com.followup.davidson.services.implementation;

import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepository personRepository;
    private IManagerService managerService;

    /**
     * Cette methode permet de lister tous les personnes de davidsons
     *
     * @return une liste des {@link Person}
     */
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * Cette methode permet de créer et sauvgarder une nouvelle personne
     *
     * @param person
     * @return personne créee
     */
    @Override
    public Person create(Person person, Long managerId) {
       Manager manager = managerService.findById(managerId);
        person.setManager(manager);
        return personRepository.save(person);
    }

    /**
     * Cette methode permet de retourner une personne par id
     *
     * @param id
     * @return une  personne
     */
    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).
                orElseThrow(() -> new ApplicationException("This person with Id" + id + "not exist"));
    }

    @Override
    public Person updatePerson(Long personId, Person person, Long managerId) {
       Manager manager = managerService.findById(managerId);
        Person personUp = new Person().builder()
                .personId(personId)
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .manager(manager)
                .build();
        personRepository.save(personUp);
        return personUp;
    }

    /**
     * Cette methode permet de supprimer une personne par id
     *
     * @param id
     */
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteInterventionByIdPerson(id);
        personRepository.deleteById(id);
    }
}
