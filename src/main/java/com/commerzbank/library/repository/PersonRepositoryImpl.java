package com.commerzbank.library.repository;

import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepositoryImpl implements RepositoryIfc<Person> {
    private List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public Person save(Person person) {
        if(person == null) {
            throw new IllegalArgumentException("rent is null");
        }
        personList.add(person);
        return person;
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personList.stream().filter(rental -> rental.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    public void deleteById(UUID id) {
    findById(id).ifPresent(personList::remove);
    }

    @Override
    public Long count() {
        return (long) personList.size();
    }
}
