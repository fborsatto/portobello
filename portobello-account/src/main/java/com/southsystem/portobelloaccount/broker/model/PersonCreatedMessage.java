package com.southsystem.portobelloaccount.broker.model;

import java.io.Serializable;

public class PersonCreatedMessage implements Serializable {

    Long personId;

    PersonTypeEnum personType;

    private Integer score;

    public PersonCreatedMessage(Long personId, PersonTypeEnum personType, Integer score) {
        this.personId = personId;
        this.personType = personType;
        this.score = score;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public PersonTypeEnum getPersonType() {
        return personType;
    }

    public void setPersonType(PersonTypeEnum personType) {
        this.personType = personType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
