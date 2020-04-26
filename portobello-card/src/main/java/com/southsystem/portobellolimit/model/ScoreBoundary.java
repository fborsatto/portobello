package com.southsystem.portobellolimit.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "scoreBoundary")
public class ScoreBoundary {

    private Integer initialBound;
    private Integer finalBound;

    private BigDecimal creditLimitValue;
    private BigDecimal accountLimitValue;

    public ScoreBoundary(Integer initialBound, Integer finalBound, BigDecimal creditLimitValue, BigDecimal accountLimitValue) {
        this.initialBound = initialBound;
        this.finalBound = finalBound;
        this.creditLimitValue = creditLimitValue;
        this.accountLimitValue = accountLimitValue;
    }

    public Integer getInitialBound() {
        return initialBound;
    }

    public void setInitialBound(Integer initialBound) {
        this.initialBound = initialBound;
    }

    public Integer getFinalBound() {
        return finalBound;
    }

    public void setFinalBound(Integer finalBound) {
        this.finalBound = finalBound;
    }

    public BigDecimal getCreditLimitValue() {
        return creditLimitValue;
    }

    public void setCreditLimitValue(BigDecimal creditLimitValue) {
        this.creditLimitValue = creditLimitValue;
    }

    public BigDecimal getAccountLimitValue() {
        return accountLimitValue;
    }

    public void setAccountLimitValue(BigDecimal accountLimitValue) {
        this.accountLimitValue = accountLimitValue;
    }
}
