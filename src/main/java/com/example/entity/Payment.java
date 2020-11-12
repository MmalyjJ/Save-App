package com.example.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "payments_save")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    private User from;

    @OneToOne
    private User to;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "day_created", nullable = false)
    private Date dayCreated;


    @Transient
    public int response_status = 0;


    public Payment() {}

    public Payment(User from, User to, Long amount, Date dayCreated) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dayCreated = dayCreated;
    }
}
