package com.example.currency.viewmodel;


import com.example.currency.api.CurrencyApi;
import com.example.currency.model.Rate;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Component
public class RatesViewModel {

    private List<Currency> currencyList=new ArrayList<>();

    public ObservableList<Rate> rates;

    public void initCurrencyList() throws URISyntaxException, IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {
        currencyList = CurrencyApi.currencyList;
    }



    public ObservableList<Rate> getRatesList(Currency base) throws URISyntaxException, NoSuchAlgorithmException, IOException, InterruptedException, KeyManagementException {
        List<Rate> list = CurrencyApi.getRatesList(base.getCurrencyCode());
        rates = FXCollections.observableArrayList(list);

        return rates.sorted();


    }

    public ObservableList<Currency> getCurrencyList(){
        return FXCollections.observableList(currencyList).sorted();
    }

    public ObservableList<Currency> req() throws URISyntaxException, IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {

        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("https://api.currencyscoop.com/v1/latest?api_key=4d08a2682d79d0d097f7031d0e7aff8a"))
                .GET()
                .build();
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null,null,null);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .sslContext(sslContext)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        JSONObject jsonResponse = new JSONObject(responseBody);

        List<String> countries = new ArrayList<>(jsonResponse.getJSONObject("response").getJSONObject("rates").toMap().keySet());

        List<Currency> currencies = new ArrayList<>();

        for(var entry : countries){
            try{
                currencies.add(Currency.getInstance(entry));
            }
            catch (Exception e){}
        }
        //list=countries;

        return FXCollections.observableList(currencies).sorted();
    }

    public ObservableList<String>
             getCountriesList(){

        Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(s -> req());

        return FXCollections.observableList(getCountriesList());
    }
}
