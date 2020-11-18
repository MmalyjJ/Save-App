package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCandle {
    private String ticker;

    private double openPrices;

    private double highPrices;

    private double lowPrices;

    private double closePrices;

    private Object volumeData;
}
