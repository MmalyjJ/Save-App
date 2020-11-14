package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "assets")
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "bought_date")
    private Date boughtDate;


    public Asset(String name, Long cost, Date boughtDate){
        this.name = name;
        this.cost = cost;
        this.boughtDate = boughtDate;
    }
}
