package com.southsystem.portobelloaccount.model.enumeration;

public enum AccountTypeEnum {

    C("Corrente"), E("Empresarial");

    private String description;

    AccountTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}