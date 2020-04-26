package com.southsystem.portobellolimit;

import com.southsystem.portobellolimit.model.Card;
import com.southsystem.portobellolimit.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Card>> getLimitByAccountId(@PathVariable(value = "id") String accountId) {
        return cardService.getCardByAccountId(accountId)
                .map(card -> ResponseEntity.ok(card))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
