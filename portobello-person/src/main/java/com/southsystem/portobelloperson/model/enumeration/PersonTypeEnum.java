package com.southsystem.portobelloperson.model.enumeration;

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