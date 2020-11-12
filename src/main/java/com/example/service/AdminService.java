package com.example.service;


import com.example.entity.Admin;
import com.example.entity.Role;
import com.example.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {
    /*МЕТОДЫ ДЛЯ РАБОТЫ С АДМИНИСТРАТОРАМИ*/

    @Autowired
    AdminRepository adminRepository;

    //Метод для регистрации нового администратора
    public Admin registerAdmin(Admin admin) {
        if(adminRepository != null && !adminRepository.exists(Example.of(admin))) {
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));

            admin.setRole(Role.ADMIN);

            return adminRepository.save(admin);
        }

        return null;
    }


    //Методы для удаления
    public void deleteAdmin(Admin admin) {
        if(adminRepository != null && adminRepository.exists(Example.of(admin)))
            adminRepository.delete(admin);
    }


    //Метод для авторизации администратора по критерию
    public Admin authorizeAdminByPhone(String phone, String password) {
        if (adminRepository != null && adminRepository.findByPhone(phone) != null) {
            Admin admin = adminRepository.findByPhone(phone);

            if (admin.getPassword().equals(password))
                return admin;
        }

        return null;
    }

    public Admin authorizeAdminByEmail(String email, String password) {
        if (adminRepository != null && adminRepository.findByEmail(email) != null) {
            Admin admin = adminRepository.findByEmail(email);

            if (admin.getPassword().equals(password))
                return admin;
        }

        return null;
    }


    //Методы для получение администратора по критерию
    public List<Admin> getAllAdmins() {
        if(adminRepository != null)
            return adminRepository.findAll();

        return null;
    }

    public Admin getAdminByPhone(String phone) {
        if(adminRepository != null)
            return adminRepository.findByPhone(phone);

        return null;
    }

    public Admin getAdminByEmail(String email) {
        if(adminRepository != null && adminRepository.findByEmail(email) != null)
            return adminRepository.findByEmail(email);

        return null;
    }

    public Admin getAdminByToken(String token) {
        if(adminRepository != null)
            adminRepository.findByToken(token);

        return null;
    }


    //Методы для изменения критерия у администратора
    public Admin changeTokenAdmin(String oldToken, String newToken, String password) {
        if(adminRepository != null && adminRepository.findByToken(oldToken) != null) {
            Admin admin = adminRepository.findByToken(oldToken);

            if(admin.getPassword().equals(password)) {
                admin.setToken(newToken);

                return adminRepository.save(admin);
            }
        }

        return null;
    }

    public Admin changePasswordAdmin(String token, String oldPassword, String newPassword) {
        if(adminRepository != null && getAdminByToken(token) != null) {
            Admin admin = getAdminByToken(token);

            if(admin.getPassword().equals(oldPassword)) {
                admin.setPassword(newPassword);

                return adminRepository.save(admin);
            }
        }

        return null;
    }

}
