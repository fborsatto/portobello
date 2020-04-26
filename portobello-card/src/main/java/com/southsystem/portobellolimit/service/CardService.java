package com.southsystem.portobellolimit.service;

import com.southsystem.portobellolimit.config.BrokerInput;
import com.southsystem.portobellolimit.model.Card;
import com.southsystem.portobellolimit.model.ScoreBoundary;
import com.southsystem.portobellolimit.model.broker.AccountCreatedMessage;
import com.southsystem.portobellolimit.repository.CardRepository;
import com.southsystem.portobellolimit.repository.ScoreBoundaryRepository;
import com.southsystem.portobellolimit.util.CreditCardNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class CardService {

    final CardRepository cardRepository;
    final ScoreBoundaryRepository scoreBoundaryRepository;

    private static final String CARD_BIN = "1234";
    private static final int CARD_LENGTH = 16;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    @Autowired
    public CardService(CardRepository cardRepository, ScoreBoundaryRepository scoreBoundaryRepository) {
        this.cardRepository = cardRepository;
        this.scoreBoundaryRepository = scoreBoundaryRepository;
    }

    @StreamListener(BrokerInput.ACCOUNT_CREATED)
    public void createCard(AccountCreatedMessage accountCreatedMessage) {
        Card card = new Card(accountCreatedMessage.getAccountId(),
                findScoreBoundary(accountCreatedMessage.getScore()).block().getCreditLimitValue(),
                CreditCardNumberGenerator.generate(CARD_BIN, CARD_LENGTH)
        );

        if (card.getCardLimit().compareTo(BigDecimal.ZERO) > 0) {
            cardRepository.save(card).subscribe(
                    it -> LOGGER.info(String.format("Card created: %s", it.getAccountId())),
                    e -> LOGGER.error("Error creating card", e)
            );
        }
    }

    public Mono<Card> getCardByAccountId(@PathVariable(value = "id") String accountId) {
        return cardRepository.findById(accountId);
    }

    private Mono<ScoreBoundary> findScoreBoundary(Integer score) {
        return scoreBoundaryRepository.findByInitialBoundLessThanEqualAndFinalBoundGreaterThanEqual(score, score);
    }
}
