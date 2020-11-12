package com.example.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "user_save")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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


    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @OneToOne(cascade = CascadeType.ALL)
    private Documents documents;


    @OneToOne
    private Wallet wallet;


    @Transient
    private Role role;

    @Transient
    private String confirmPassword;


    public User(){}

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
