package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.PersonCreateDto;
import com.commerzbank.library.dto.response.PersonDto;
import com.commerzbank.library.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    ResponseEntity<PersonDto> addPerson(@RequestBody PersonCreateDto personCreateDto) {
        return ResponseEntity.ok(personService.savePerson(personCreateDto));
    }

}
