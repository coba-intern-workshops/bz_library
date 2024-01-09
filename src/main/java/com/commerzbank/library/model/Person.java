package com.commerzbank.library.model;

import java.util.Objects;
import java.util.UUID;

public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private UserType userType;

    public Person(String firstName, String lastName, UserType userType) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && userType == person.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userType);
    }
}
