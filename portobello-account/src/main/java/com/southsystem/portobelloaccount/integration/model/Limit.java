package com.southsystem.portobelloaccount.integration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "limit")
public class Limit {

    @Id
    private String accountId;

    private BigDecimal accountLimit;

    public Limit(String accountId, BigDecimal accountLimit) {
        this.accountId = accountId;
        this.accountLimit = accountLimit;
    }

    public Limit() {
        
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
    }
}
