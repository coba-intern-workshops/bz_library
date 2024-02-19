package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.request.PersonCreateDto;
import com.commerzbank.library.model.Person;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonCreateMapper extends Mapper<PersonCreateDto, Person> {
    public PersonCreateMapper() {
        super(PersonCreateMapper::mapToEntity, PersonCreateMapper::mapToDto);
    }

    private static PersonCreateDto mapToDto(Person person) {
        return new PersonCreateDto(person.getFirstName(), person.getLastName(), person.getUserType());
    }

    private static Person mapToEntity(PersonCreateDto personCreateDto) {
        return new Person(UUID.randomUUID(), personCreateDto.firstName(), personCreateDto.lastName(), personCreateDto.userType());
    }
}
