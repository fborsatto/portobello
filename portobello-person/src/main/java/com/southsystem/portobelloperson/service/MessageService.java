package com.southsystem.portobelloperson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.portobelloperson.config.BrokerOutput;
import com.southsystem.portobelloperson.model.broker.PersonCreatedMessage;
import com.southsystem.portobelloperson.model.enumeration.PersonTypeEnum;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    public void sendPersonCreatedMessage(Long personId, PersonTypeEnum personTypeEnum, Integer score) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Message message = MessageBuilder.withPayload(mapper.writeValueAsBytes(new PersonCreatedMessage(personId, personTypeEnum, score))).build();
            brokerOutput.personCreatedChannel().send(message);
        } catch (JsonProcessingException e) {
            LOGGER.error("Send person message error", e);
        }

    }
}
