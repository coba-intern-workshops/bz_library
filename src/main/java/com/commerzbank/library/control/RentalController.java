package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.ExtendRentDto;
import com.commerzbank.library.dto.request.RentalCreateDto;
import com.commerzbank.library.dto.response.RentalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.commerzbank.library.service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/rentals")
class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    ResponseEntity<List<RentalDto>> findAllRentals() {
        return ResponseEntity.ok(rentalService.findAllRentals());
    }

    @PostMapping
    ResponseEntity<RentalDto> addReservation(@RequestBody RentalCreateDto rentalCreateDto) {
        return ResponseEntity.ok(rentalService.saveRental(rentalCreateDto));
    }

    @PatchMapping("/{id}")
    ResponseEntity<RentalDto> returnBook(@PathVariable String id) {
        return ResponseEntity.ok(rentalService.returnBook(id));
    }

    @PatchMapping
    ResponseEntity<RentalDto> extendRent(@RequestBody ExtendRentDto extendRentDto) {
        return ResponseEntity.ok(rentalService.extendRent(extendRentDto));
    }


}
