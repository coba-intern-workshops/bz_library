package com.commerzbank.library.service;

public class PersonSearchCriteria {
    private String firsName;
    private String lastName;

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PersonSearchCriteria(String firsName, String lastName) {
        this.firsName = firsName;
        this.lastName = lastName;
    }
}
