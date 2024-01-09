package com.commerzbank.library.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
    private UUID id;
    private Book book;
    private Person person;
    private LocalDate rentedOn;
    private LocalDate rentedUntil;
    private LocalDate returnedOn;
    private boolean returned;

    public Rental(Book book, Person person, LocalDate rentedOn, LocalDate rentedUntil, LocalDate returnedOn, boolean returned) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.person = person;
        this.rentedOn = rentedOn;
        this.rentedUntil = rentedUntil;
        this.returnedOn = returnedOn;
        this.returned = returned;
    }

    public Book getBook() {
        return book;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getRentedOn() {
        return rentedOn;
    }

    public LocalDate getRentedUntil() {
        return rentedUntil;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public boolean isReturned() {
        return returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return returned == rental.returned && Objects.equals(id, rental.id) && Objects.equals(book, rental.book) && Objects.equals(person, rental.person) && Objects.equals(rentedOn, rental.rentedOn) && Objects.equals(rentedUntil, rental.rentedUntil) && Objects.equals(returnedOn, rental.returnedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, person, rentedOn, rentedUntil, returnedOn, returned);
    }
}
