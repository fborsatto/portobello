package com.southsystem.portobelloaccount.broker.model;

public enum PersonTypeEnum {

    PF("Pessoal Física"), PJ("Pessoa Jurídica");

    private String description;

    PersonTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}