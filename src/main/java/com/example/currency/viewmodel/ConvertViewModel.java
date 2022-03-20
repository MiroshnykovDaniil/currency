package com.example.currency.viewmodel;

import com.example.currency.api.CurrencyApi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ConvertViewModel {

    private List<Currency> currencyList=new ArrayList<>();

    public void initCurrencyList() throws URISyntaxException, IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {
        currencyList = CurrencyApi.currencyList;
    }

    public ObservableList<Currency> getCurrencyList(){
        return FXCollections.observableList(currencyList).sorted();
    }

    public void exchange(Currency fromCurrency, Currency toCurrency, BigDecimal value){

    }

}
