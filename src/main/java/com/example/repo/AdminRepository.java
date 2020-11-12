package com.example.repo;


import com.example.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByPhone(String phone);
    Admin findByToken(String token);
    Admin findByEmail(String email);
}
