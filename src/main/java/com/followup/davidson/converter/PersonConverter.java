package com.followup.davidson.converter;

import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.services.IManagerService;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements GenericsConverter<Person, PersonDto> {

    private IManagerService managerService;

    public PersonConverter(IManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public PersonDto entityToDto(Person person) {
     return PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }


    public Person dtoToEntity(PersonDto personDto) {
        Manager manager = managerService.findById(personDto.getManagerId());
        return Person.builder()
                .personId(personDto.getPersonId())
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .manager(manager)
                .build();
    }

}
