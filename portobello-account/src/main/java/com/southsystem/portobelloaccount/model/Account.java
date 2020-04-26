package com.southsystem.portobelloaccount.model;

import com.southsystem.portobelloaccount.model.enumeration.AccountTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {

    @Id
    private String accountId;

    private String agency;

    private AccountTypeEnum accountType;

    private Long personId;

    public Account(String accountId, String agency, AccountTypeEnum accountType, Long personId) {
        this.accountId = accountId;
        this.agency = agency;
        this.accountType = accountType;
        this.personId = personId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
