package com.southsystem.portobelloaccount.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

    String PERSON_CREATED = "personCreated";

    @Input(BrokerInput.PERSON_CREATED)
    SubscribableChannel personCreatedQueue();
}
