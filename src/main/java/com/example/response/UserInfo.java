package com.example.response;


import com.example.entity.Portfolio;
import com.example.entity.Profile;
import com.example.entity.User;
import com.example.entity.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserInfo {
    private String email;

    private String phone;

    private Profile profile;

    private Portfolio portfolio;

    private Wallet wallet;


    public UserInfo(User user) {
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.profile = user.getProfile();
        this.portfolio = user.getPortfolio();
        this.wallet = user.getWallet();
    }
}
