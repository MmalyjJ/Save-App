package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private User from;

    @OneToOne(cascade = CascadeType.ALL)
    private User to;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "day_created", nullable = false)
    private Date dayCreated;


    @Transient
    public int response_status = 0;


    public Payment(User from, User to, Long amount, Date dayCreated) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dayCreated = dayCreated;
    }
}
