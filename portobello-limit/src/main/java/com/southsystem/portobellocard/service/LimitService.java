package com.southsystem.portobellocard.service;

import com.southsystem.portobellocard.config.BrokerInput;
import com.southsystem.portobellocard.model.Limit;
import com.southsystem.portobellocard.model.ScoreBoundary;
import com.southsystem.portobellocard.model.broker.AccountCreatedMessage;
import com.southsystem.portobellocard.repository.LimitRepository;
import com.southsystem.portobellocard.repository.ScoreBoundaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Service
public class LimitService {

    final LimitRepository limitRepository;
    final ScoreBoundaryRepository scoreBoundaryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(LimitService.class);

    @Autowired
    public LimitService(LimitRepository limitRepository,  ScoreBoundaryRepository scoreBoundaryRepository) {
        this.limitRepository = limitRepository;
        this.scoreBoundaryRepository = scoreBoundaryRepository;
    }

    @StreamListener(BrokerInput.ACCOUNT_CREATED)
    public void createLimit(AccountCreatedMessage accountCreatedMessage) {
        Limit limit = new Limit(accountCreatedMessage.getAccountId(),
                findAccountLimit(accountCreatedMessage.getScore()).block().getAccountLimitValue());

        limitRepository.save(limit).subscribe(
                it-> LOGGER.info(String.format("Limit created: %s", it.getAccountId())),
                e -> LOGGER.error("Error creating limit", e)
        );
    }

    public Mono<Limit> getLimitByAccountId(@PathVariable(value = "id") String accountId) {
        return limitRepository.findById(accountId);
    }

    private Mono<ScoreBoundary> findAccountLimit(Integer score) {
        return scoreBoundaryRepository.findByInitialBoundLessThanEqualAndFinalBoundGreaterThanEqual(score, score);
    }
}
