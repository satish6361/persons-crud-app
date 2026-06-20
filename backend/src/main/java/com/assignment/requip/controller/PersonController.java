package com.assignment.requip.controller;

import com.assignment.requip.dto.PersonDto;
import com.assignment.requip.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(
            @Valid @RequestBody PersonDto personDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createPerson(personDto));
    }

    @GetMapping
    public ResponseEntity<Page<PersonDto>> getAllPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        return ResponseEntity.ok(
                personService.getAllPersons(page, size, sortBy)
        );
    }

}
