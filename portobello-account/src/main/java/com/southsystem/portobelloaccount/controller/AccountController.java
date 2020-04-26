package com.southsystem.portobelloaccount.controller;

import com.southsystem.portobelloaccount.model.dto.AccountResponseDTO;
import com.southsystem.portobelloaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public Flux<AccountResponseDTO> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AccountResponseDTO>> getAccountById(@PathVariable(value = "id") String accountId) {
        return accountService.getAccountById(accountId)
                .map(savedAccount -> ResponseEntity.ok(savedAccount))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
