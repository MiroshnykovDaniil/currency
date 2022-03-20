package com.example.currency.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Rate {

    public Currency currency;
    public BigDecimal rate;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Rate(Currency currency, BigDecimal rate) {
        this.currency = currency;
        this.rate = rate;
    }
}
