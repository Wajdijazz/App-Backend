package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.exceptions.ApplicationException;

import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.PersonRepository;

import com.followup.davidson.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepository personRepository;
    private PersonConverter personConverter;

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
     * @param personDto
     * @return personne créee
     */
    @Override
    public PersonDto createOrUpdate(PersonDto personDto) {
        return personConverter.entityToDto(personRepository.save(personConverter.dtoToEntity(personDto)));
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
