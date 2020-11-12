package com.example.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users_documents_save")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "passport_data")
    private String passportData;

    @Column(name = "passport_issued")
    private String passportIssued;

    @Column(name = "passport_main_page")
    private String passportMainPage;

    @Column(name = "passport_registration_page")
    private String passportRegistrationPage;

    @Column(name = "is_accepted")
    private Boolean isAccepted = false;


    @Transient
    public int response_status = 0;
}
