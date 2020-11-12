package com.example.repo;


import com.example.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
    UserAuth getByUserName(String userName);
}
