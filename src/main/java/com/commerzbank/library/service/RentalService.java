package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.ExtendRentDto;
import com.commerzbank.library.dto.request.RentalCreateDto;
import com.commerzbank.library.exception.NotOwnerTryingToExtendReservationException;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.dto.response.RentalDto;
import com.commerzbank.library.mapper.RentalsMapper;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepositoryImpl rentalRepository;

    private final Mapper<RentalDto, Rental> rentalMapper;

    private final BookService bookService;

    private final PersonService personService;

    private final RentalsMapper rentalsMapper;

    public RentalService(RentalRepositoryImpl rentalRepository, Mapper<RentalDto, Rental>  rentalMapper, BookService bookService, PersonService personService, RentalsMapper rentalsMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.personService = personService;
        this.bookService = bookService;
        this.rentalsMapper = rentalsMapper;
    }

    public List<RentalDto> findRentals(RentalSearchCriteria rentalSearchCriteria) {
        List<Rental> rentalList = rentalRepository.findAll().stream().filter(rental -> rental.getPerson().getFirstName().equals(rentalSearchCriteria.getFirsName()) )
                .filter(rental ->  rental.getPerson().getLastName().equals(rentalSearchCriteria.getLastName())).toList();

        return rentalList.stream().map(rentalMapper::mapFromEntity).toList();
    }

    public List<RentalDto> findAllRentals() {
        List<Rental> rentalList = rentalRepository.findAll();

        return rentalList.stream().map(rentalMapper::mapFromEntity).toList();
    }

    public RentalDto saveRental(RentalCreateDto rentalCreateDto) {
        final Book book = bookService.findByTitleAndStatus(rentalCreateDto.title(), BookStatus.AVAILABLE);
        final Person person = personService.findPersonById(rentalCreateDto.personId());

        var result = rentalRepository.save(rentalsMapper.toEntity(book,person));
        book.setBookStatus(BookStatus.RENTED);

        return rentalsMapper.toDto(result);
    }

    public RentalDto returnBook(String id) {
    final Book book = bookService.findById(id);
    final Rental rental = rentalRepository.findByStatusAndBookId(book, false).orElseThrow(() -> new RecordNotFoundException("reservation not found"));

    book.setBookStatus(BookStatus.AVAILABLE);

    rental.setReturned(true);
    rental.setReturnedOn(LocalDate.now());
    rentalRepository.save(rental);

    return rentalsMapper.toDto(rental);
    }

    public RentalDto extendRent(ExtendRentDto extendRentDto) {
    final Book book = bookService.findById(extendRentDto.bookId());
    final Rental rental = rentalRepository.findByStatusAndBookId(book, false).orElseThrow(() -> new RecordNotFoundException("reservation not found"));

    if (rental.getPerson().getId().toString().equals(extendRentDto.personId())) {
        throw new NotOwnerTryingToExtendReservationException("You did not rent this book");
    }

    rental.setRentedUntil(rental.getRentedUntil().plusDays(7));
    rentalRepository.save(rental);

    return rentalsMapper.toDto(rental);
    }
}
