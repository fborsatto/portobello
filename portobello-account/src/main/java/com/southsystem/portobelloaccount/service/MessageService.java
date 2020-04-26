package com.southsystem.portobelloaccount.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.portobelloaccount.config.BrokerOutput;
import com.southsystem.portobelloaccount.broker.model.AccountCreatedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private BrokerOutput brokerOutput;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    public void sendAccountCreatedMessage(String accountId, Integer score) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            AccountCreatedMessage accountCreatedMessage = new AccountCreatedMessage(accountId, score);
            Message message = MessageBuilder.withPayload(mapper.writeValueAsBytes(accountCreatedMessage)).build();
            brokerOutput.accountCreatedChannel().send(message);
        } catch (JsonProcessingException e) {
            LOGGER.error("Send account message error", e);
        }

    }
}
