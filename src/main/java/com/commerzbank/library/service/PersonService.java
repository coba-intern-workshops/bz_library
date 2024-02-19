package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.PersonCreateDto;
import com.commerzbank.library.dto.response.PersonDto;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.repository.PersonRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepositoryImpl personRepository;

    private final Mapper<PersonDto, Person> personMapper;
    private final Mapper<PersonCreateDto, Person> personCreateMapper;


    public PersonService(PersonRepositoryImpl personRepository, Mapper<PersonDto, Person>  personMapper,Mapper<PersonCreateDto, Person> personCreateMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.personCreateMapper = personCreateMapper;
    }

    public List<PersonDto> findRentals(RentalSearchCriteria rentalSearchCriteria) {
        List<Person> rentalList = personRepository.findAll().stream().filter(person -> person.getFirstName().equals(rentalSearchCriteria.getFirsName()) )
                .filter(person ->  person.getLastName().equals(rentalSearchCriteria.getLastName())).toList();

        return rentalList.stream().map(personMapper::mapFromEntity).toList();
    }

    public List<PersonDto> findAllPeople() {
        List<Person> personList = personRepository.findAll();

        return personList.stream().map(personMapper::mapFromEntity).toList();
    }

    public PersonDto savePerson(PersonCreateDto personCreateDto) {
        var result = personRepository.save(personCreateMapper.mapFromDto(personCreateDto));
        return personMapper.mapFromEntity(result);
    }

    public Person findPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Person not found"));
    }
}
