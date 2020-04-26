package com.southsystem.portobelloperson.model;

import com.southsystem.portobelloperson.util.NumberUtils;
import com.southsystem.portobelloperson.model.enumeration.PersonTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Document(collection = "person")
public class Person {

    @Transient
    public static final String SEQUENCE_NAME = "person_sequence";

    @Id
    private Long personId;

    @NotBlank(message = "Nome da pessoa deve ser informado")
    private String name;

    @NotNull(message = "Tipo da pessoa deve ser informado")
    private PersonTypeEnum personType;

    @NotNull
    @Size(max = 14, message = "Documento não pode ter mais de 14 dígitos")
    private String document;

    private Integer score;

    public Person(Long personId, @NotBlank String name, @NotNull PersonTypeEnum personType, @NotNull @Size(max = 14) String document) {
        this.personId = personId;
        this.name = name;
        this.personType = personType;
        this.document = document;
        this.score = NumberUtils.getRandomNumberInRange(0,9);
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public PersonTypeEnum getPersonType() {
        return personType;
    }

    public String getDocument() {
        return document;
    }

    public Integer getScore() {
        return score;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
