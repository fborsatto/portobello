package com.southsystem.portobelloperson.service;

import com.southsystem.portobelloperson.exception.PersonException;
import com.southsystem.portobelloperson.model.Person;
import com.southsystem.portobelloperson.repository.PersonRepository;
import com.southsystem.portobelloperson.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    MessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    public Flux<Person> findAll() {
        Flux<Person> fallback = Flux.error(new PersonException("Nenhuma pessoa encontrada"));
        return personRepository.findAll().switchIfEmpty(fallback);
    }

    public Mono<Person> createPerson(Person person) throws PersonException {
        ValidationUtils.validateDocument(person.getDocument(), person.getPersonType());
        person.setPersonId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));
        messageService.sendPersonCreatedMessage(person.getPersonId(), person.getPersonType(), person.getScore());
        LOGGER.info("PESSOA CRIADA");
        return personRepository.save(person);
    }

    public Mono<Person> getPersonById(@PathVariable(value = "id") Long personId) {
        return personRepository.findById(personId);
    }


}
