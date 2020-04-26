package com.southsystem.portobellolimit.repository;

import com.southsystem.portobellolimit.model.ScoreBoundary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ScoreBoundaryRepository extends ReactiveMongoRepository<ScoreBoundary, String> {

    Mono<ScoreBoundary> findByInitialBoundLessThanEqualAndFinalBoundGreaterThanEqual(Integer initialBound, Integer finalBound);
}

