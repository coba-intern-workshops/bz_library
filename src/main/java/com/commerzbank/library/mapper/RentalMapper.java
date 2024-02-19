package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.response.RentalDto;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RentalMapper extends Mapper<RentalDto, Rental>  {


    public RentalMapper(Function<RentalDto, Rental> toEntity, Function<Rental, RentalDto> toDto) {
        super(toEntity, toDto);

    }

    public RentalMapper() {
        super(RentalMapper::mapToEntity, RentalMapper::mapToDto);
    }

    private static RentalDto mapToDto(Rental rental) {
        return new RentalDto(rental.getId(),new BookMapper().mapFromEntity(rental.getBook()),new PersonMapper().mapFromEntity(rental.getPerson()),rental.getRentedOn(),rental.getRentedUntil(),rental.getReturnedOn(),rental.isReturned());
    }

    private static Rental mapToEntity(RentalDto rentalDto) {
        return new Rental(new BookMapper().mapFromDto(rentalDto.book()),new PersonMapper().mapFromDto(rentalDto.person()),rentalDto.rentedOn(),rentalDto.rentedUntil(),rentalDto.returnedOn(),rentalDto.returned());
    }
}
