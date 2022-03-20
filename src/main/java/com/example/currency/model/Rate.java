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


/*
    public ObjectProperty<Currency> currency;
    public ObjectProperty<BigDecimal> rate;

    public Currency getCurrency() {
        return currency.get();
    }

    public void setCurrency(Currency currency) {
        currencyProperty().set(currency);
    }

    public BigDecimal getRate() {
        return rate.get();
    }

    public ObjectProperty<Currency> currencyProperty(){
        return currency;
    }

    public ObjectProperty<BigDecimal> rateProperty(){
        return rate;
    }

    public void setRate(BigDecimal rate) {
        rateProperty().set(rate);
    }

    public Rate(Currency currency, BigDecimal rate) {
        this.currency.set(currency);
        this.rate.set(rate);
//        this.currency = currency;
//        this.rate = rate;
    }
 */