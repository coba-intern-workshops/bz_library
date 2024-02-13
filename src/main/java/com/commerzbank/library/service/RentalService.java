package com.commerzbank.library.service;

import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.dto.request.RentalDto;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    private final RentalRepositoryImpl rentalRepository;

    private final Mapper<RentalDto, Rental> rentalMapper;

    public RentalService(RentalRepositoryImpl rentalRepository, Mapper<RentalDto, Rental>  rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
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
}
