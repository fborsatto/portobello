package com.southsystem.portobelloaccount.service;

import com.southsystem.portobelloaccount.exception.AccountException;
import com.southsystem.portobelloaccount.integration.client.CardClient;
import com.southsystem.portobelloaccount.integration.client.LimitClient;
import com.southsystem.portobelloaccount.integration.model.Card;
import com.southsystem.portobelloaccount.integration.model.Limit;
import com.southsystem.portobelloaccount.model.Account;
import com.southsystem.portobelloaccount.model.dto.AccountResponseDTO;
import com.southsystem.portobelloaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LimitClient limitClient;

    @Autowired
    CardClient cardClient;

    public Flux<AccountResponseDTO> findAll() {
        Flux<Account> fallback = Flux.error(new AccountException("Nenhuma conta encontrada"));
        Flux<Account> accountFlux = accountRepository.findAll().switchIfEmpty(fallback);

        return accountFlux.flatMap(this::buildAccountResponse);
    }

    public Mono<AccountResponseDTO> getAccountById(@PathVariable(value = "id") String accountId) {
        Mono<Account> account = accountRepository.findById(accountId).subscribeOn(Schedulers.elastic());
        Mono<Limit> limit = limitClient.getAccountLimit(accountId).subscribeOn(Schedulers.elastic()).onErrorReturn(new Limit());
        Mono<Card> card = cardClient.getCard(accountId).subscribeOn(Schedulers.elastic()).onErrorReturn(new Card());

        return Mono.zip(account, limit, card).map(tuple -> new AccountResponseDTO(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }


    private Mono<AccountResponseDTO> buildAccountResponse(Account account) {
        Mono<Limit> limit = limitClient.getAccountLimit(account.getAccountId()).subscribeOn(Schedulers.elastic()).onErrorReturn(new Limit());
        Mono<Card> card = cardClient.getCard(account.getAccountId()).subscribeOn(Schedulers.elastic()).onErrorReturn(new Card());
        Mono<Account> accountMono = Mono.just(account);
        return Mono.zip(accountMono, limit, card).map(tuple -> new AccountResponseDTO(tuple.getT1(), tuple.getT2(), tuple.getT3()));
   }
}