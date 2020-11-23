package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "day_created")
    private Date dayCreated;


    @OneToOne(cascade = CascadeType.ALL)//, mappedBy = "profiles")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)//, mappedBy = "portfolios")
    private Portfolio portfolio;

    @OneToOne(cascade = CascadeType.ALL)//, mappedBy = "wallets")
    private Wallet wallet;


    @Transient
    private Role role;

    @Transient
    private String confirmPassword;


    public User(String password, String phone, String email, String token) {
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.dayCreated = new Date();
    }


    public User(UserAuth userAuth) {
        this.password = userAuth.getPassword();
        this.email = userAuth.getEmail();
        this.role = userAuth.getRole();
        this.dayCreated = new Date();
    }
}
