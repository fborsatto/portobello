package com.southsystem.portobellolimit.repository;

import com.southsystem.portobellolimit.model.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, String> {

}
