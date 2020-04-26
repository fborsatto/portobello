package com.southsystem.portobelloperson.repository;

import com.southsystem.portobelloperson.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends ReactiveMongoRepository<Person, Long> {

}
