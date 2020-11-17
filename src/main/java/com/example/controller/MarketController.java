package com.example.controller;


import com.example.entity.Stock;
import com.example.marketApi.MarketAPI;
import com.example.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketAPI marketAPI;


    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public RestResponse<Stock> getMarketStock(@RequestParam("symbol") String symbol) {
        Stock stock = marketAPI.getMarketStock(symbol);

        if(stock == null)
            return new RestResponse<>(null, "MARKET DATA ERROR", 10);

        return new RestResponse<Stock>(stock, "ALL RIGHT", 0);
    }
}
