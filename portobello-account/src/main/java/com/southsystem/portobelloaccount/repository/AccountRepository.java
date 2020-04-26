package com.southsystem.portobelloaccount.repository;

import com.southsystem.portobelloaccount.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

}
