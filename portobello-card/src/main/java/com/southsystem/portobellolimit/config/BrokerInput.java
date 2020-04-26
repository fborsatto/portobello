package com.southsystem.portobellolimit.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

    String ACCOUNT_CREATED = "accountCreated";

    @Input(BrokerInput.ACCOUNT_CREATED)
    SubscribableChannel accountCreated();
}
