package com.commerzbank.library.repository;

import com.commerzbank.library.model.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

class RentalRepositoryImplTest implements WithAssertions {
    private RepositoryIfc<Rental> rentalRepository;

    @BeforeEach
        void setUp() {rentalRepository = new RentalRepositoryImpl();}

    @Test
    void getAllRents_whenListIsNotEmpty_thenReturnRentList() {
        List<Rental> rentalList = rentalRepository.findAll();
        assertThat(rentalList).isEmpty();
    }

    @Test
    void rentBook_whenAllDataValid() {
        Long size = rentalRepository.count();
        Rental rental = getTestRental();
        rentalRepository.save(rental);

        assertThat(rentalRepository.count()).isEqualTo(size+1);
    }

    @Test
    void rentBook_whenAllDataValid_thenCompareRentalObjects() {
        Rental rental = getTestRental();;
        Rental savedRental = rentalRepository.save(rental);

//        assertEquals(rental,savedRental);
        assertThat(savedRental).isEqualTo(rental);

        assertThat(savedRental.getBook()).matches(book -> book.getTitle().equals("Programowanie Aplikacji Bazodanowych w Hibernate"))
                .matches(book -> book.getAuthor().equals("Christian Bauer"));

        assertThat(savedRental.getPerson().getFirstName()).startsWith("Joh").contains("n").doesNotContain("y");

//        assertThat(savedRental.getRentedOn()).as("Checking rental date for user %s %s", savedRental.getPerson().getFirstName(),savedRental.getPerson().getLastName())
//                .withFailMessage("should be: "+LocalDate.now())
//                .isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void addBook_whenDataNotValid_thenThrowException() {
//        assertThrows(IllegalArgumentException.class,
//                () -> rentalRepository.save(null));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(()-> rentalRepository.save(null)).withMessage("rent is null");
    }

//    @Test
//    void deleteRent_whenRentxists_thenDeleteRent() {
//        Rental rental = getTestRental();
//        final Long rentListSize = rentalRepository.count();
//
//        rentalRepository.delete(rental);
//        assertEquals(rentListSize - 1, rentalRepository.count());
//    }

    private static Rental getTestRental() {
        Book book = new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c60"),"Programowanie Aplikacji Bazodanowych w Hibernate", "Christian Bauer",BookStatus.LOST);
        Person person = new Person("John","Doe", UserType.USER);

        return new Rental(book,person, LocalDate.now(),LocalDate.now().plusWeeks(4));
    }
}