package com.southsystem.portobellocard.controller;

import com.southsystem.portobellocard.model.Limit;
import com.southsystem.portobellocard.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/limit")
public class LimitController {

    @Autowired
    LimitService limitService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Limit>> getLimitByAccountId(@PathVariable(value = "id") String accountId) {
        return limitService.getLimitByAccountId(accountId)
                .map(limit -> ResponseEntity.ok(limit))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
