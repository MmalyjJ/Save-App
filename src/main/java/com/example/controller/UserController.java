package com.example.controller;

import com.example.entity.Payment;
import com.example.response.RestResponse;
import com.example.entity.User;
import com.example.entity.Wallet;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


//    @RequestMapping(value = "/make-payment", method = {RequestMethod.GET, RequestMethod.POST})
//    public Payment makePayment(@RequestParam("token") String token, @RequestParam("/wallet-id-from") Integer walletIdFrom,
//                               @RequestParam("to-id") Integer toId, @RequestParam("/wallet-id-to") Integer walletIdTo,
//                               @RequestParam("amount") Long amount) {
//        return userService.makePayment(token, walletIdFrom, toId, walletIdTo, amount);
//    }

    @RequestMapping(value = "/get-info-by-token", method = RequestMethod.GET)
    public RestResponse getInfoByToken(@RequestParam("token") String token) {
        User user =userService.getUserByToken(token);

        if(user == null)
            return new RestResponse(null, "TOKEN PROBLEM", 3);

        return new RestResponse(user, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "get-wallet-by-user", method = RequestMethod.GET)
    public RestResponse getWalletByUser(@RequestParam("/token") String token) {
        Wallet wallet = userService.getWalletByUser(token);

        if(wallet == null)
            return new RestResponse(null, "WALLET PROBLEM", 7);

        return new RestResponse(wallet, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "get-all-payments", method = RequestMethod.GET)
    public RestResponse getAllPaymentsByUser(@RequestParam("/token") String token) {
        List<Payment> payments = userService.getAllPaymentsByUser(token);

        if(payments == null)
            return new RestResponse(null, "PAYMENTS PROBLEM", 8);

        return new RestResponse(payments, "ALL RIGHT", 0);
    }
}
