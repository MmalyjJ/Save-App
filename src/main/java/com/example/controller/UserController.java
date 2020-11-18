package com.example.controller;

import com.example.entity.Payment;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.Wallet;
import com.example.response.PaymentInfo;
import com.example.response.RestResponse;
import com.example.response.UserInfo;
import com.example.response.WalletInfo;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/user")
    public String forUser() {
        return "user";
    }


    @RequestMapping(value = "/make-payment", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResponse<Payment> makePayment(@RequestParam("token") String token, @RequestParam("to-id") Integer toId,
                               @RequestParam("amount") Long amount) {
        Payment payment = userService.makePayment(token, toId, amount);

        if(payment == null)
            return new RestResponse<>(null, "PAYMENT PROBLEM", 8);

        return new RestResponse<Payment>(payment, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "/get-info-by-token", method = RequestMethod.GET)
    public RestResponse<UserInfo> getInfoByToken(@RequestParam("token") String token) {
        User user =userService.getUserByToken(token);

        user.setRole(Role.USER);
        user.setConfirmPassword(user.getPassword());

        UserInfo userInfo = new UserInfo(user);

        if(user == null)
            return new RestResponse<UserInfo>(null, "TOKEN PROBLEM", 3);

        return new RestResponse<UserInfo>(userInfo, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "get-wallet-by-user", method = RequestMethod.GET)
    public RestResponse<WalletInfo> getWalletByUser(@RequestParam("/token") String token) {
        Wallet wallet = userService.getWalletByUser(token);
        WalletInfo walletInfo = new WalletInfo(wallet);

        if(wallet == null)
            return new RestResponse<>(null, "WALLET PROBLEM", 7);

        return new RestResponse<WalletInfo>(walletInfo, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "get-all-payments", method = RequestMethod.GET)
    public RestResponse<List<PaymentInfo>> getAllPaymentsByUser(@RequestParam("/token") String token) {
        List<Payment> payments = userService.getAllPaymentsByUser(token);
        List<PaymentInfo> paymentInfos = new ArrayList<PaymentInfo>();

        for (Payment payment : payments)
            paymentInfos.add(new PaymentInfo(payment));

        if(payments == null)
            return new RestResponse<>(null, "PAYMENTS PROBLEM", 8);

        return new RestResponse<List<PaymentInfo>>(paymentInfos, "ALL RIGHT", 0);
    }
}
