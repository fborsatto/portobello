package com.southsystem.portobellocard.repository;

import com.southsystem.portobellocard.model.Limit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends ReactiveMongoRepository<Limit, String> {

}
