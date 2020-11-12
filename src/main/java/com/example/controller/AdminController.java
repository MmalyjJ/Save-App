package com.example.controller;


import com.example.entity.Admin;
import com.example.entity.Portfolio;
import com.example.entity.Profile;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    /*МЕТОДЫ ДЛЯ РАБОТЫ С АДМИНИСТРАТОРАМИ*/

    @GetMapping("/admin")
    public String forAdmin() {
        return "admin";
    }


    @RequestMapping(value = "/register-admin", method = { RequestMethod.GET, RequestMethod.POST })
    public Admin registerAdmin(@RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email,
                               @RequestParam(value = "token") String token, @RequestParam(value = "password") String password,
                               @RequestParam(value = "confirm-password") String confirmPassword) {
        if(password.equals(confirmPassword))
            return adminService.registerAdmin(new Admin(phone, password, email, token, new Date()));

        return null;
    }

//
//    @RequestMapping(value = "/auth-admin-by-phone", method = RequestMethod.GET)
//    public Admin authorizeAdminByPhone(@RequestParam(value = "phone") String phone, @RequestParam(value = "password") String password) {
//        return adminService.authorizeAdminByPhone(phone, password);
//    }
//
//
//    @RequestMapping(value = "/auth-admin-by-email", method = RequestMethod.GET)
//    public Admin authorizeAdminByEmail(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
//        return adminService.authorizeAdminByEmail(email, password);
//    }


    @RequestMapping(value = "/get-all-admins", method = RequestMethod.GET)
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }


    @RequestMapping(value = "/get-admin-by-phone", method = RequestMethod.GET)
    public Admin getAdminByPhone(@RequestParam(value = "phone") String phone) {
        return adminService.getAdminByPhone(phone);
    }


    @RequestMapping(value = "/get-admin-by-email", method = RequestMethod.GET)
    public Admin getAdminByEmail(@RequestParam(value = "email") String email) {
        return adminService.getAdminByEmail(email);
    }


    @RequestMapping(value = "/get-admin-by-token", method = RequestMethod.GET)
    public Admin getAdminByToken(@RequestParam(value = "token") String token) {
        return adminService.getAdminByToken(token);
    }


    @RequestMapping(value = "/update-token-admin", method = { RequestMethod.GET, RequestMethod.PUT })
    public Admin changeTokenAdmin(@RequestParam(value = "old-token") String oldToken, @RequestParam("new-token")String newToken,
                                  @RequestParam("password") String password) {
        return adminService.changeTokenAdmin(oldToken, newToken, password);
    }


    @RequestMapping(value = "/update-password-admin", method = { RequestMethod.GET, RequestMethod.PUT })
    public Admin changePasswordAdmin(@RequestParam("token") String token, @RequestParam("old-password") String oldPassword,
                                     @RequestParam("new-password") String newPassword) {
        return adminService.changePasswordAdmin (token, oldPassword, newPassword);
    }



    /*МЕТОДЫ ДЛЯ РАБОТЫ С ПОТЛЬЗОВАТЕЛЯМИ*/

    @RequestMapping(value = "/get-all-users", method = RequestMethod.GET)
    public List<User> getAllUsersByToken() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get-user-by-phone", method = RequestMethod.GET)
    public User getUserByPhone(@RequestParam(value = "phone") String phone) {
        return userService.getUserByPhone(phone);
    }


    @RequestMapping(value = "/get-user-by-token",  method = RequestMethod.GET)
    public User checkUserByToken(@RequestParam(value = "token") String token) {
        return userService.getUserByToken(token);
    }


    @RequestMapping(value = "/update-token-user", method = { RequestMethod.GET, RequestMethod.PUT })
    public void
    updateToken(@RequestParam(value = "id") Integer id, @RequestParam(value = "new-token") String newToken){
        userService.changeTokenUser(id, newToken);
    }


    @RequestMapping(value = "/update-password-user", method = { RequestMethod.GET, RequestMethod.PUT })
    public User changePassword(@RequestParam(value = "token") String token, @RequestParam(value = "old-password") String oldPassword,
                               @RequestParam(value = "new-password") String newPassword){
        return userService.changePasswordUser(token, oldPassword, newPassword);
    }


    @RequestMapping(value = "/delete-user", method = { RequestMethod.GET, RequestMethod.DELETE})
    public void deleteUser(@RequestParam(value = "id") Integer id) {
        userService.deleteUserById(id);
    }



    /*МЕТОДЫ ДЛЯ РАБОТЫ С ПРОФИЛЯМИ ПОЛЬЗОВАТЕЛЕЙ*/

    @RequestMapping(value = "/set-user-profile", method = { RequestMethod.GET, RequestMethod.PUT })
    public Profile setProfile(@RequestParam("id") Integer id, @RequestParam(value = "first_name") String firstName,
                              @RequestParam(value = "second_name") String secondName, @RequestParam(value = "birth-day") String birthDay){
        return userService.setNewProfile(id, new Profile(firstName, secondName, birthDay));
    }


    @RequestMapping(value = "/get-user-profile", method = RequestMethod.GET)
    public Profile getProfile(@RequestParam(value = "token") String token){
        return userService.getProfileByUserToken(token);
    }


    @RequestMapping(value = "/get-user-portfolio", method = RequestMethod.GET)
    public Portfolio getPortfolio(@RequestParam(value = "token") String token){
        return userService.getUserByToken(token).getPortfolio();
    }
}
