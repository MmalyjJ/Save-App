package com.example.marketApi;


import com.example.entity.Currency;
import com.example.entity.Stock;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


@Service
public class MarketAPI {
    private static final String URL_ADDRESS = "https://finnhub.io/api/v1/stock/profile2?symbol=";
    private static final String API_KEY = "&token=bupacc748v6sjkjiudq0";


    public Stock getMarketStock(String symbol)  {
        Stock stock = new Stock();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS + symbol + API_KEY);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        System.out.println(new JSONObject(content.toString()));

        JSONObject jsonObject = new JSONObject(content.toString());


        stock.setTicker(jsonObject.getString("ticker"));
        stock.setCost(jsonObject.getLong("marketCapitalization"));
        stock.setCountry(jsonObject.getString("country"));
        stock.setPhone(jsonObject.getString("phone"));
        stock.setUrl(jsonObject.getString("weburl"));
        stock.setName(jsonObject.getString("name"));
        stock.setLogo(jsonObject.getString("logo"));
        stock.setCurrency(Currency.USD);
        stock.setShareOutstanding(jsonObject.getDouble("shareOutstanding"));


        return stock;
    }
}
