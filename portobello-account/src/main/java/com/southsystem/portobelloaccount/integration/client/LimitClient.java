package com.southsystem.portobelloaccount.integration.client;

import com.southsystem.portobelloaccount.integration.model.Limit;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LimitClient {
    private final WebClient webClient;

    public LimitClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://portobello-limit:8083/api").build();
    }

    public Mono<Limit> getAccountLimit(String accountId) {
        return this.webClient.get().uri("/limit/{id}", accountId)
                .retrieve().bodyToMono(Limit.class).defaultIfEmpty(new Limit());
    }
}