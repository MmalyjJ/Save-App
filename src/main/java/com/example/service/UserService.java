package com.example.service;


import com.example.entity.*;
import com.example.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    PaymentsRepository paymentsRepository;


    /*МЕТОДЫ ДЛЯ РАБОТЫ С ПОЛЬЗОВАТЕЛЯМИ */

    //    Регистрация нового пользователя
    public User registerUser(User user) {
        if (userRepository != null && !userRepository.exists(Example.of(user))) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

            user.setRole(Role.USER);

            return userRepository.save(user);
        }

        return null;
    }


    //Удаление пользователя
    public void deleteUserById(Integer id) {
        if (userRepository != null && userRepository.findById(id) != null){
            userRepository.delete(getUserById(id));
        }
    }

    public void deleteAllUsers() {
        if(userRepository != null)
            userRepository.deleteAll();
    }


    //Получение всех пользователей по критерию
    public User getUserById(Integer id) {
        if(userRepository != null)
            return userRepository.getOne(id);

        return null;
    }

    public List<User> getAllUsers() {
        if(userRepository != null)
            return userRepository.findAll();

        return null;
    }

    public User getUserByPhone(String phone) {
        if(userRepository != null)
            return userRepository.findByPhone(phone);

        return null;
    }

    public User getUserByToken(String token) {
        if(userRepository != null && userRepository.findByToken(token) != null)
            return userRepository.findByToken(token);

//        throw new UserNotFoundException("USER WITH SUCH TOKEN IS NOT FOUND!");
        return null;
    }

    public User getUserByEmail(String email) {
        if(userRepository != null)
            return userRepository.findByEmail(email);

        return null;
    }


    //Метод для изменение проперти Пользователя
    public User changeTokenUser(Integer id, String newToken) {
        if(userRepository != null && getUserById(id) != null) {
            User user = getUserById(id);

            userRepository.delete(user);

            user.setToken(newToken);

            return userRepository.save(user);
        }

        return null;
    }

    public User changePasswordUser(String token, String oldPassword, String newPassword) {
        if(userRepository != null) {
            User user = getUserByToken(token);

            if(user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);

                userRepository.save(user);
            }
        }

        return null;
    }



    /*МЕТОДЫ ДЛЯ РАБОТЫ С ПРОФИЛЯМИ ПОЛЬЗОВАТЕЛЕЙ*/

    @Autowired
    ProfileRepository profileRepository;

    //Установить новый профайл
    public Profile setNewProfile(Integer id, Profile profile) {
        if(profileRepository != null && userRepository.getOne(id) != null)
            return profileRepository.save(profile);

        return null;
    }


    //Получение разных профилей по критериям
    public Profile getProfileByUserToken(String token) {
        if(profileRepository != null && getUserByToken(token) != null)
            return getUserByToken(token).getProfile();

        return null;
    }


    /*МЕТОДЫ ДЛЯ РАБОТЫ С ПОРТФОЛИО ПОЛЬЗОВАТЕЛЕЙ*/

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    StockRepository stockRepository;

    //Метод для регистрацци нового Портфолио

    public Portfolio addPortfolio(Portfolio portfolio) {
        if(portfolioRepository != null)
            return portfolioRepository.save(portfolio);

        return null;
    }


    //Метод для получения портволио по критерию

    public Portfolio getPortfolioByUserId(Integer id) {
        if(portfolioRepository != null) {
            if (getUserById(id) != null)
                return getUserById(id).getPortfolio();
        }

        return null;
    }

//    public Portfolio buyNewAssets(Integer id, Integer howMuch, String nameAsset) {
//        if(portfolioRepository != null && userRepository != null && assetRepository.findByName(nameAsset) != null) {
//            User user = userRepository.getOne(id);
//            Portfolio portfolio = user.getPortfolio();
//            Asset asset = assetRepository.findByName(nameAsset);
//
//            List<Asset> assets;
//
//            for(int i )
//
//            user.getWallet().setAmount(user.getWallet().getAmount() - );
//
//            portfolio.addAssets(howMuch, assetRepository.findByName(nameAsset));
//        }
//    }


    /*МЕТОДЫ ДЛЯ РАБОТЫ С КОШЕЛЬКАМИ */

    //Создание новой оплаты
//    public Payment makePayment(String token, Integer toId, Long amount) {
//        if(userRepository != null && walletRepository != null && paymentsRepository != null) {
//            User from = userRepository.findByToken(token);
//            User to = userRepository.getOne(toId);
//
//            Wallet walletFrom = from.getWallet();
//            Wallet walletTo = to.getWallet();
//
//            Payment payment;
//
//            if(walletFrom != null && walletTo != null) {
//                if(walletFrom.getAmount() >= amount) {
//                    walletFrom.setAmount(walletFrom.getAmount() - amount);
//                    walletTo.setAmount(walletTo.getAmount() + amount);
//
//                    payment = new Payment(from, to, amount, new Date());
//
//                    walletFrom.addNewPayment(payment);
//                    walletTo.addNewPayment(payment);
//
//                    from.setWallet(walletFrom);
//                    to.setWallet(walletTo);
//
//                    userRepository.save(from);
//                    userRepository.save(to);
//
//                    walletRepository.save()
//                }
//            }
//        }
//
//        return null;
//    }


    //Метод для получения по критерию
    public Wallet getWalletByUser(String token) {
        if(userRepository != null) {
            return userRepository.findByToken(token).getWallet();
        }

        return null;
    }

    public List<Payment> getAllPaymentsByUser(String token) {
        if(userRepository != null && walletRepository != null && paymentsRepository != null) {
            return paymentsRepository.findAllByFromToken(token);
        }

        return null;
    }

}
