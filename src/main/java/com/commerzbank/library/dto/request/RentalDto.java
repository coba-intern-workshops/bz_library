package com.commerzbank.library.dto.request;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.Person;

import java.time.LocalDate;
import java.util.UUID;

public record RentalDto(UUID id, BookDto book, PersonDto person, LocalDate rentedOn, LocalDate rentedUntil, LocalDate returnedOn, boolean returned) {
}