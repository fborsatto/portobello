package com.southsystem.portobelloaccount.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountParameterConfig {
    @Value("${default.agency}")
    private String agency;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
