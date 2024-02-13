package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.PersonDto;
import com.commerzbank.library.dto.request.RentalDto;
import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.PersonRepositoryImpl;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepositoryImpl personRepository;

    private final Mapper<PersonDto, Person> personMapper;

    public PersonService(PersonRepositoryImpl personRepository, Mapper<PersonDto, Person>  personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
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
}
