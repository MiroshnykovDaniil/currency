package com.example.currency.api;

import com.example.currency.model.Rate;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CurrencyApi
{
    public static List<Currency> currencyList;

    public static List<Currency> getCurrencyList() throws URISyntaxException, IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {

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
        currencyList=currencies;
        return currencies;
    }

    public static BigDecimal convert(String from, String to, String amount) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
        String url = "https://api.currencyscoop.com/v1/convert?"+"from="+from+"&to="+to+"&amount="+amount+"&api_key=4d08a2682d79d0d097f7031d0e7aff8a";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
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

        System.out.println(jsonResponse.getJSONObject("response").getBigDecimal("value"));

        return jsonResponse.getJSONObject("response").getBigDecimal("value");
    }

    public static List<Rate> getRatesList(String targetCurrency) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
        String url = "https://api.currencyscoop.com/v1/latest?"+"api_key=4d08a2682d79d0d097f7031d0e7aff8a"+"&base="+targetCurrency;

//        for(var currency : currencies){url+=currency+",";}
//        // delete latest ',' symbol
//        url = url.substring(0,url.length()-1);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
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

        //return jsonResponse.getJSONObject("response").getBigDecimal("value");
        HashMap<String,BigDecimal> map =
        new HashMap(jsonResponse.getJSONObject("response").getJSONObject("rates").toMap());

        List<Rate> rates = new ArrayList<>();
        for(Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            try{
            Currency key = Currency.getInstance(entry.getKey());
            BigDecimal value = entry.getValue();
            rates.add(new Rate(key,value));
            }
            catch (Exception e){}
        }



        return rates;
    }


   // https://api.currencyscoop.com/v1/convert
}
