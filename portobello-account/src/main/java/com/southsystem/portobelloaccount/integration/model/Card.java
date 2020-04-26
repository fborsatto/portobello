package com.southsystem.portobelloaccount.integration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "card")
public class Card {

    @Id
    private String accountId;

    private BigDecimal cardLimit;

    private String cardNumber;


    public Card(String accountId, BigDecimal cardLimit, String cardNumber) {
        this.accountId = accountId;
        this.cardLimit = cardLimit;
        this.cardNumber = cardNumber;
    }

    public Card() {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(BigDecimal cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
