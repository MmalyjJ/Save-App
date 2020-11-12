package com.example.controller;


import com.example.entity.Admin;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserAuth;
import com.example.service.AdminService;
import com.example.service.UserAuthService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserAuthService userAuthService;


    @GetMapping("/registration")
    public String registerNewUser() {
        return "registration";
    }


    @PostMapping("/registration")
    public String registerNewUser(String name, String phone, String email, String password, String confirmPassword, String token) {
        UserAuth userAuth = new UserAuth(name, email, password, confirmPassword);
        User user = new User(userAuth);
        user.setPhone(phone);
        user.setToken(token);

        if(password.equals(confirmPassword)) {
            userAuthService.registerUserAuth(userAuth);
            userService.registerUser(user);

            return "redirect:/login";
        }

        return "registration";
    }
}
