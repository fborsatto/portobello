package com.southsystem.portobelloaccount.integration.client;

import com.southsystem.portobelloaccount.integration.model.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CardClient {
    private final WebClient webClient;

    public CardClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://portobello-card:8084/api").build();
    }

    public Mono<Card> getCard(String accountId) {
        return this.webClient.get().uri("/card/{id}", accountId)
                .retrieve().bodyToMono(Card.class).defaultIfEmpty(new Card());
    }
}