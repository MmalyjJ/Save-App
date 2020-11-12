package com.example.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "user_wallets_save")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "wallet_payments",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    private List<Payment> payments;


    public Wallet(){}

    public Wallet(Long amount, Type type) {
        this.amount = amount;
        this.type = type;
    }


    private enum Type {
        USD, EURO, BY,RB
    }
}
