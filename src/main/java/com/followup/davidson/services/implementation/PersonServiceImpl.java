package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.ManagerConverter;
import com.followup.davidson.converter.PersonConverter;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.exceptions.ApplicationException;

import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.PersonRepository;

import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.IManagerService;
import com.followup.davidson.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Transactional
@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepository personRepository;
    private PersonConverter personConverter;
    private TJRepository tjRepository;
    private IManagerService managerService;

    /**
     * Cette methode permet de lister tous les personnes de davidsons
     *
     * @return une liste des {@link Person}
     */
    @Override
    public List<PersonDto> findAll() {
        return personConverter.entityListToDtoList(personRepository.findAll());
    }

    /**
     * Cette methode permet de créer et sauvgarder une nouvelle personne
     *
     * @param personDto
     * @return personne créee
     */
    @Override
    public PersonDto createOrUpdate(PersonDto personDto,Long managerId) {
        personDto.setManagerDto(managerService.findById(managerId));
        return personConverter.entityToDto(personRepository.save(personConverter.dtoToEntity(personDto)));
    }

    @Override
    public PersonDto updateIsActiveByPersonId(Long personId, Boolean isActive) {
        PersonDto personDto = findById(personId);
        personDto.setActive(isActive);

        return personConverter.entityToDto(personRepository.save(personConverter.dtoToEntity(personDto)));
    }

    /**
     * Cette methode permet de retourner une personne par id
     *
     * @param id
     * @return une  personne
     */
    @Override
    public PersonDto findById(Long id) {
        return personConverter.entityToDto(personRepository.findById(id).
                orElseThrow(() -> new ApplicationException("This person with Id" + id + "not exist")));
    }

    /**
     * Cette methode permet de supprimer une personne par id
     *
     * @param id
     */
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteInterventionByIdPerson(id);
        tjRepository.deleteByPerson_PersonId(id);
        personRepository.deleteById(id);
    }
}
