package com.example.controller;

import com.example.entity.Payment;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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


//    @RequestMapping(value = "get-all-wallets", method = RequestMethod.GET)
//    public Wallet getAllWalletsByUser(@RequestParam("/token") String token) {
//        return userService.getAllWalletsByUser(token);
//    }


    @RequestMapping(value = "get-all-payments", method = RequestMethod.GET)
    public List<Payment> getAllPaymentsByUser(@RequestParam("/token") String token) {
        return userService.getAllPaymentsByUser(token);
    }
}