package com.example.controller;


import com.example.entity.Stock;
import com.example.entity.StockCandle;
import com.example.marketApi.MarketAPI;
import com.example.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketAPI marketAPI;


    @RequestMapping(value = "/stock-info", method = RequestMethod.GET)
    public RestResponse<Stock> getMarketStockInfo(@RequestParam("ticker") String ticker) {
        Stock stock = marketAPI.getMarketStockInfo(ticker);

        if(stock == null)
            return new RestResponse<>(null, "STOCK PROBLEM", 13);

        return new RestResponse<Stock>(stock, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "/stock-candles-eod", method = RequestMethod.GET)
    public RestResponse<List<StockCandle>> getMarketStockCandlesEndOfDay(@RequestParam("ticker") String ticker,
                                                                         @RequestParam("date") String date) {
        List<StockCandle> stockCandles = marketAPI.getMarketStockCandleEndOfDay(ticker, date);

        if(stockCandles == null)
            return new RestResponse<>(null, "STOCK PROBLEM", 12);

        return new RestResponse<List<StockCandle>>(stockCandles, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "/stock-candles-intraday", method = RequestMethod.GET)
    public String getMarketStockCandles(@RequestParam("ticker") String ticker) {
//        List<StockCandle> stockCandles = marketAPI.getMarketStockCandlesIntraday(ticker);
//
//        if(stockCandles == null)
//            return new RestResponse<>(null, "STOCK PROBLEM", 12);
//
//        return new RestResponse<List<StockCandle>>(stockCandles, "ALL RIGHT", 0);
        return marketAPI.getMarketStockCandlesIntraday(ticker);
    }


    @RequestMapping(value = "/stock-candles-intraday-interval", method = RequestMethod.GET)
    public RestResponse<List<StockCandle>> getMarketStockCandlesIntradayInterval(@RequestParam("ticker") String ticker,
                                                                                 @RequestParam("interval") String interval) {
        List<StockCandle> stockCandles = marketAPI.getMarketStockCandlesIntradayInterval(ticker, interval);

        if(stockCandles == null)
            return new RestResponse<>(null, "STOCK PROBLEM", 12);

        return new RestResponse<List<StockCandle>>(stockCandles, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "/stock-candles-interval", method = RequestMethod.GET)
    public RestResponse<List<StockCandle>> getMarketStockCandlesInterval(@RequestParam("ticker") String ticker,
                                                                         @RequestParam("from") String from,
                                                                         @RequestParam("to") String to) {
        List<StockCandle> stockCandles = marketAPI.getMarketStockCandleInterval(ticker, from, to);

        if(stockCandles == null)
            return new RestResponse<>(null, "STOCK PROBLEM", 12);

        return new RestResponse<List<StockCandle>>(stockCandles, "ALL RIGHT", 0);
    }
}