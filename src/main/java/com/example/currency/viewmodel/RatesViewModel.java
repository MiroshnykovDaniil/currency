package com.example.currency.viewmodel;


import com.example.currency.api.CurrencyApi;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void initCurrencyList() throws URISyntaxException, IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {
        currencyList = CurrencyApi.currencyList;
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

//    public void t() throws IOException {
//        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=80587f94bc224e188938780c90a29f32");
//        try (InputStream is = url.openStream();
//             JsonReader rdr = Json.createReader(is)) {
//
//            JsonObject obj = rdr.readObject();
//                System.out.println(obj.toString());
//                 JsonArray results = obj.getJsonArray("rates");
//                 for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//                         System.out.print(result.getJsonObject("from").getString("name"));
//                         System.out.print(": ");
//                         System.out.println(result.getString("message", ""));
//                         System.out.println("-----------");
//                    }
//             }
//    }

    public ObservableList<String>
             getCountriesList(){

        Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(s -> req());

        return FXCollections.observableList(getCountriesList());
    }
}
