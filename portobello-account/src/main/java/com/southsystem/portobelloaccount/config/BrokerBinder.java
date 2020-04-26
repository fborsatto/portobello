package com.southsystem.portobelloaccount.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({BrokerInput.class, BrokerOutput.class})
public class BrokerBinder {

}
