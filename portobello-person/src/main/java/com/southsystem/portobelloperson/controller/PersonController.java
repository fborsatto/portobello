package com.southsystem.portobelloperson.controller;

import com.southsystem.portobelloperson.exception.PersonException;
import com.southsystem.portobelloperson.model.Person;
import com.southsystem.portobelloperson.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public Flux<Person> getAllPersons() {
        return personService.findAll();
    }

    @PostMapping
    public Mono<Person> createPerson(@Valid @RequestBody Person person) throws PersonException {
        return personService.createPerson(person);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> getPersonById(@PathVariable(value = "id") Long personId) {
        return personService.getPersonById(personId)
                .map(savedPerson -> ResponseEntity.ok(savedPerson))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
