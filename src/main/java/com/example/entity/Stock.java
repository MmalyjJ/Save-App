package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "bought_date")
    private Date boughtDate;

    @Column(name = "industry")
    private String industry;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "shareOutstanding")
    private double shareOutstanding;


    public Stock(String ticker, Long cost, Date boughtDate){
        this.ticker = ticker;
        this.cost = cost;
        this.boughtDate = boughtDate;
    }
}
