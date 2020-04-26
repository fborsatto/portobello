package com.southsystem.portobelloperson.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BrokerOutput {

    String PERSON_CREATED = "personCreated";

    @Output(BrokerOutput.PERSON_CREATED)
    MessageChannel personCreatedChannel();
}
