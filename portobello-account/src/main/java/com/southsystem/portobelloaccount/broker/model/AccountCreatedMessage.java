package com.southsystem.portobelloaccount.broker.model;

import java.io.Serializable;

public class AccountCreatedMessage implements Serializable {

    String accountId;

    Integer score;

    public AccountCreatedMessage(String accountId, Integer score) {
        this.accountId = accountId;
        this.score = score;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
