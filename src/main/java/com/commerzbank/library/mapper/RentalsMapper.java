package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.response.RentalDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class RentalsMapper {

    private final BookMapper bookMapper;

    private final PersonMapper personMapper;

    public RentalsMapper(BookMapper bookMapper, PersonMapper personMapper) {
        this.bookMapper = bookMapper;
        this.personMapper = personMapper;
    }

    public RentalDto toDto(Rental rental) {
        return new RentalDto(UUID.randomUUID(),
                bookMapper.mapFromEntity(rental.getBook()),
                personMapper.mapFromEntity(rental.getPerson()),
                null,
                LocalDate.now().plusDays(7L),
                null,
                false
                );
    }

    public Rental toEntity(Book book, Person person) {
        return new Rental(
                book,
                person,
                null,
                LocalDate.now().plusDays(7L),
                null,
                false
        );
    }
}
