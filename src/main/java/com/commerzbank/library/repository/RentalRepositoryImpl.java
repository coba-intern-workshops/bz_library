package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RentalRepositoryImpl implements RepositoryIfc<Rental> {
    private List<Rental> rentalList = new ArrayList<>();

    @Override
    public List<Rental> findAll() {
        return rentalList;
    }

    @Override
    public Rental save(Rental rental) {
        if(rental == null) {
            throw new IllegalArgumentException("rent is null");
        }
        rentalList.add(rental);
        return rental;
    }

    @Override
    public Optional<Rental> findById(UUID id) {
        return rentalList.stream().filter(rental -> rental.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Rental rental) {
        rentalList.remove(rental);
    }

    @Override
    public void deleteById(UUID id) {
    findById(id).ifPresent(rentalList::remove);
    }

    @Override
    public Long count() {
        return (long) rentalList.size();
    }

    public Optional<Rental> findByStatusAndBookId(Book book, boolean rentalStatus) {
        return rentalList.stream().filter(rental -> rental.getBook().equals(book) && rental.isReturned() == rentalStatus).findFirst();
    }
}
