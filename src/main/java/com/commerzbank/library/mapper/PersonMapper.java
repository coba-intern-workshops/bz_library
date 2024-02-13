package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.request.PersonDto;
import com.commerzbank.library.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper extends Mapper<PersonDto, Person>  {

    public PersonMapper() {
        super(PersonMapper::mapToEntity, PersonMapper::mapToDto);
    }

    private static PersonDto mapToDto(Person person) {
        return new PersonDto(person.getId(),person.getFirstName(),person.getLastName(),person.getUserType());
    }

    private static Person mapToEntity(PersonDto personDto) {
        return new Person(personDto.id(),personDto.firstName(),personDto.lastName(),personDto.userType());
    }
}
