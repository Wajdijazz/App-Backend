package com.followup.davidson.converter;

import com.followup.davidson.model.Person;
import com.followup.davidson.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements GenericsConverter<Person, PersonDto> {

    @Override
    public PersonDto entityToDto(Person person) {
        PersonDto personDto = PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
        return personDto;
    }

    @Override
    public Person dtoToEntity(PersonDto personDto) {
        return null;
    }

}
