package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Currency currency;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wallet_payments",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    private List<Payment> payments;


    public Wallet(Long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public void addNewPayment(Payment payment) {
        this.payments.add(payment);
    }
}
