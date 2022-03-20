package com.example.currency.api;

import org.json.JSONObject;

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

}
