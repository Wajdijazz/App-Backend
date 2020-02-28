package com.followup.davidson.converter;

import com.followup.davidson.model.Person;
import com.followup.davidson.dto.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonConverter implements GenericsConverter<Person, PersonDto> {

    private ManagerConverter managerConverter;

    @Override
    public PersonDto entityToDto(Person person) {
        return PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .isActive(person.isActive())
                .managerDto(managerConverter.entityToDto(person.getManager()))
                .build();
    }


    public Person dtoToEntity(PersonDto personDto) {
        return Person.builder()
                .personId(personDto.getPersonId())
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .isActive(personDto.isActive())
                .manager(managerConverter.dtoToEntity(personDto.getManagerDto()))
                .build();
    }

}
