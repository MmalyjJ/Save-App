package com.example.response;


import com.example.entity.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class ProfileInfo {
    private String firstName;

    private String lastName;

    private Date birthDay;


    public ProfileInfo(Profile profile) {
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.birthDay = profile.getBirthDay();
    }
}
