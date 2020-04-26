package com.southsystem.portobelloaccount.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BrokerOutput {

    String ACCOUNT_CREATED = "accountCreated";

    @Output(BrokerOutput.ACCOUNT_CREATED)
    MessageChannel accountCreatedChannel();
}
