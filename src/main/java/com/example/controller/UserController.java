package com.example.controller;

import com.example.entity.Payment;
import com.example.entity.User;
import com.example.entity.Wallet;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;


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
    public ResponseEntity<User> getInfoByToken(@RequestParam("token") String token) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(userService.getUserByToken(token) == null) {
            httpHeaders.add("message", "TOKEN ERROR");
            httpHeaders.add("resultCode", "3");
            return new ResponseEntity<User>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        httpHeaders.add("message", "ALL RIGHT");
        httpHeaders.add("resultCode", "0");
        return new ResponseEntity<User>(userService.getUserByToken(token), httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "get-wallet-by-user", method = RequestMethod.GET)
    public Wallet getAllWalletsByUser(@RequestParam("/token") String token) {
        return userService.getWalletByUser(token);
    }


    @RequestMapping(value = "get-all-payments", method = RequestMethod.GET)
    public List<Payment> getAllPaymentsByUser(@RequestParam("/token") String token) {
        return userService.getAllPaymentsByUser(token);
    }
}
