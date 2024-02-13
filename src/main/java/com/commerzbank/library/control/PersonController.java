package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.PersonDto;
import com.commerzbank.library.dto.request.RentalDto;
import com.commerzbank.library.service.PersonService;
import com.commerzbank.library.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    ResponseEntity<List<PersonDto>> findAllPeople() {
        return ResponseEntity.ok(personService.findAllPeople());
    }

}
