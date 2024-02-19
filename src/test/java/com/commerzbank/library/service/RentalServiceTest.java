package com.commerzbank.library.service;

import com.commerzbank.library.dto.response.RentalDto;
import com.commerzbank.library.model.*;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import com.commerzbank.library.mapper.RentalMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest implements WithAssertions {


    private RentalRepositoryImpl rentalRepository = mock(RentalRepositoryImpl.class);

    @Spy
    private RentalMapper rentalMapper;

    @InjectMocks
    private RentalService rentalService;
    Rental testRental1;
    Rental testRental2;

    @BeforeEach
    void setUp() {
        testRental1 = getTestRental();
        testRental2 = getTestRental();
        when(rentalRepository.findAll()).thenReturn(List.of(testRental1,testRental2));
    }
    @Test
    void shouldReturnListOfRentals() {

        when(rentalRepository.findAll()).thenReturn(List.of(testRental1,testRental2));

       List<RentalDto> rentals =  rentalService.findRentals(new RentalSearchCriteria("John","Doe"));

       int EXPECTED_SIZE = 2;

       assertThat(rentals).isNotEmpty();
       assertThat(rentals.size()).isEqualTo(EXPECTED_SIZE);
       verify(rentalMapper, times(2)).mapFromEntity(any());

    }

    private static Rental getTestRental() {
        Book book = new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c60"),"Programowanie Aplikacji Bazodanowych w Hibernate", "Christian Bauer", BookStatus.LOST);
        Person person = new Person("John","Doe", UserType.USER);

        return new Rental(book,person, LocalDate.now(),LocalDate.now().plusWeeks(4));
    }
}