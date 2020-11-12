package com.example.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "user_portfolio_save")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "total_balance_now")
    private long totalBalanceNow;

    @Column(name="total_balance_prev_day")
    private Long totalBalancePrevDay;

    @Column(name = "trade_history")
    private String tradeHistory;

    @ManyToMany
    @JoinTable(
            name = "portfolio_assets",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private List<Asset> assets;


    public void addAssets(List<Asset> assets) {
        for (Asset asset : assets) {
            
        }
    }
}
