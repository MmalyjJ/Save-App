package com.example.response;


import com.example.entity.Payment;
import com.example.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class PaymentInfo {
    private User from;

    private User to;

    private Long amount;

    private Date dayCreated;


    public PaymentInfo(Payment payment) {
        this.from = payment.getFrom();
        this.to = payment.getTo();
        this.amount = payment.getAmount();
        this.dayCreated = payment.getDayCreated();
    }
}
