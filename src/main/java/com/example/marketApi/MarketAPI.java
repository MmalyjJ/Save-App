package com.example.marketApi;


import com.example.entity.Currency;
import com.example.entity.Stock;
import com.example.entity.StockCandle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Service
public class MarketAPI {
    private static final String API_KEY = "apiKey=JOD_Y4F3PP0kiIR7OcufsRl_lXUAQ8jP";


    public Stock getMarketStockInfo(String ticker) {
        String URL_ADDRESS = "https://api.polygon.io/v1/meta/symbols/" + ticker + "/company?apiKey=JOD_Y4F3PP0kiIR7OcufsRl_lXUAQ8jP";

        Stock stock = new Stock();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        JSONObject jsonObject = new JSONObject(content.toString());

        stock.setTicker(jsonObject.getString("symbol"));
        stock.setLogo(jsonObject.optString("logo"));
        stock.setCountry(jsonObject.optString("country"));
        stock.setIndustry(jsonObject.optString("industry"));
        stock.setPhone(jsonObject.optString("phone"));
        stock.setUrl(jsonObject.optString("url"));
        stock.setDescription(jsonObject.optString("description"));
        stock.setLogo(jsonObject.optString("logo"));
        stock.setName(jsonObject.optString("name"));
        stock.setCurrency(Currency.USD);

        return stock;
    }


    public List<StockCandle> getMarketStockCandlesIntraday(String ticker) {
        String URL_ADDRESS = "http://api.marketstack.com/v1/intraday?access_key=11af2a4edff7b1a911b13dcd1b72d4b7&symbols=" + ticker;

        StockCandle stockCandle;
        List<StockCandle> stockCandles = new ArrayList<>();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data"));

        for (int i = 0; i < jsonArray.length(); i++) {
            stockCandle = new StockCandle();

            stockCandle.setTicker(jsonArray.getJSONObject(i).getString("symbol"));
            stockCandle.setOpenPrices(jsonArray.getJSONObject(i).getDouble("open"));
            stockCandle.setHighPrices(jsonArray.getJSONObject(i).getDouble("high"));
            stockCandle.setLowPrices(jsonArray.getJSONObject(i).getDouble("low"));
            stockCandle.setClosePrices(jsonArray.getJSONObject(i).getDouble("close"));
//            stockCandle.setVolumeData(jsonArray.getJSONObject(i).get("volume"));

            stockCandles.add(stockCandle);
        }

        return stockCandles;
    }


    public List<StockCandle> getMarketStockCandlesIntradayInterval(String ticker, String interval) {
        String URL_ADDRESS = "https://api.marketstack.com/v1/intraday?access_key=11af2a4edff7b1a911b13dcd1b72d4b7&" +
                "symbols=" + ticker + "&interval=" + interval;

        StockCandle stockCandle;
        List<StockCandle> stockCandles = new ArrayList<>();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data"));

        for (int i = 0; i < jsonArray.length(); i++) {
            stockCandle = new StockCandle();

            stockCandle.setTicker(jsonArray.getJSONObject(i).getString("symbol"));
            stockCandle.setOpenPrices(jsonArray.getJSONObject(i).getDouble("open"));
            stockCandle.setHighPrices(jsonArray.getJSONObject(i).getDouble("high"));
            stockCandle.setLowPrices(jsonArray.getJSONObject(i).getDouble("low"));
            stockCandle.setClosePrices(jsonArray.getJSONObject(i).getDouble("close"));
//            stockCandle.setVolumeData(jsonArray.getJSONObject(i).get("volume"));

            stockCandles.add(stockCandle);
        }

        return stockCandles;
    }


    //YYYY-MM-DD
    public List<StockCandle> getMarketStockCandleEndOfDay(String ticker, String date) {
        String URL_ADDRESS = "https://api.marketstack.com/v1/eod/" + date +
                "?access_key=11af2a4edff7b1a911b13dcd1b72d4b7&symbols=" + ticker;

        StockCandle stockCandle;
        List<StockCandle> stockCandles = new ArrayList<>();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data"));

        for (int i = 0; i < jsonArray.length(); i++) {
            stockCandle = new StockCandle();

            stockCandle.setTicker(jsonArray.getJSONObject(i).getString("symbol"));
            stockCandle.setOpenPrices(jsonArray.getJSONObject(i).getDouble("open"));
            stockCandle.setHighPrices(jsonArray.getJSONObject(i).getDouble("high"));
            stockCandle.setLowPrices(jsonArray.getJSONObject(i).getDouble("low"));
            stockCandle.setClosePrices(jsonArray.getJSONObject(i).getDouble("close"));
//            stockCandle.setVolumeData(jsonArray.getJSONObject(i).get("volume"));

            stockCandles.add(stockCandle);
        }

        return stockCandles;
    }



    public List<StockCandle> getMarketStockCandleInterval(String ticker, String from, String to) {
        String URL_ADDRESS = "https://api.marketstack.com/v1/eod?access_key=11af2a4edff7b1a911b13dcd1b72d4b7&symbols=" + ticker +
                "&" + from + "&" + to;

        StockCandle stockCandle;
        List<StockCandle> stockCandles = new ArrayList<>();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("data"));

        for (int i = 0; i < jsonArray.length(); i++) {
            stockCandle = new StockCandle();

            stockCandle.setTicker(jsonArray.getJSONObject(i).getString("symbol"));
            stockCandle.setOpenPrices(jsonArray.getJSONObject(i).getDouble("open"));
            stockCandle.setHighPrices(jsonArray.getJSONObject(i).getDouble("high"));
            stockCandle.setLowPrices(jsonArray.getJSONObject(i).getDouble("low"));
            stockCandle.setClosePrices(jsonArray.getJSONObject(i).getDouble("close"));
//            stockCandle.setVolumeData(jsonArray.getJSONObject(i).get("volume"));

            stockCandles.add(stockCandle);
        }

        return stockCandles;
    }
}
