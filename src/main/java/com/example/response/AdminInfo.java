package com.example.response;


import com.example.entity.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AdminInfo {
    private String email;

    private String phone;


    public AdminInfo(Admin admin) {
        this.email = admin.getEmail();
        this.phone = admin.getPhone();
    }
}
