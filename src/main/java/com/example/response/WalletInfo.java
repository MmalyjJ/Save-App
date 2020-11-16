package com.example.response;


import com.example.entity.Currency;
import com.example.entity.Payment;
import com.example.entity.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class WalletInfo {
    private Long amount;

    private Currency currency;

    private List<Payment> payments;


    public WalletInfo(Wallet wallet) {
        this.amount = wallet.getAmount();
        this.currency = wallet.getCurrency();
        this.payments = wallet.getPayments();
    }
}
