package com.southsystem.portobelloperson.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({BrokerOutput.class})
public class BrokerBinder {

}
