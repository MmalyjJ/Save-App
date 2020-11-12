package com.example.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users_profiles_save")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "birth_date")
    private String birthDay;

    @Transient
    public int response_status = 0;


    public Profile(){}

    public Profile(String firstName, String secondName, String birthDay) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDay = birthDay;
    }
}