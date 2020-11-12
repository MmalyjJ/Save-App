package com.example.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "bought_date")
    private Date boughtDate;


    public Asset(){}

    public Asset(String name, Long cost, Date boughtDate){
        this.name = name;
        this.cost = cost;
        this.boughtDate = boughtDate;
    }
}
