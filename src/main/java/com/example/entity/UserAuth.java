package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user_auth")
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Column(name = "email")
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "enabled")
    private int enabled;

    @Transient
    private Role role;


    public UserAuth(String name, String email, String password, String confirmPassword) {
        this.userName = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.confirmPassword = new BCryptPasswordEncoder().encode(confirmPassword);
        this.enabled = 1;
        this.role = Role.USER;
    }
}
