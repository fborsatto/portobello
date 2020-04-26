package com.southsystem.portobelloaccount.model.dto;

import com.southsystem.portobelloaccount.integration.model.Card;
import com.southsystem.portobelloaccount.integration.model.Limit;
import com.southsystem.portobelloaccount.model.Account;

public class AccountResponseDTO {

    private Account account;

    private Limit limit;

    private Card card;

    public AccountResponseDTO(Account account, Limit limit, Card card) {
        this.account = account;
        this.limit = limit;
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
