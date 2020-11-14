package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "admins_save")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "day_created")
    private Date dayCreated;


    @Transient
    private Role role;


    public Admin(String phone, String password, String email, String token, Date dayCreated) {
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.token = token;
        this.dayCreated = dayCreated;
        this.role = Role.ADMIN;
    }
}
