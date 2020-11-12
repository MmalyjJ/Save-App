package com.example.service;


import com.example.entity.UserAuth;
import com.example.repo.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAuthService {
    @Autowired
    UserAuthRepository userAuthRepository;


    public UserAuth registerUserAuth(UserAuth userAuth) {
        if(userAuthRepository != null && userAuthRepository.getByUserName(userAuth.getUserName()) == null)
            return userAuthRepository.save(userAuth);

        return null;
    }


    public UserAuth loginUserAuth(UserAuth userAuth) {
        if(userAuthRepository != null && userAuthRepository.getByUserName(userAuth.getUserName()) != null)
            return userAuth;

        return null;
    }


    public void deleteAllUsersAuth() {
        userAuthRepository.deleteAll();
    }
}
