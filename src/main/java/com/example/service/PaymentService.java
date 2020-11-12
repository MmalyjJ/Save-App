package com.example.service;


import com.example.repo.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    @Autowired
    PaymentsRepository paymentsRepository;

    @Autowired
    UserService userService;


//
//    public Payment makePayment(String token, Integer toId, Long amount) {
//        if(paymentsRepository != null && userService.checkUserByToken(token)) {
//            User from = userService.getUserByToken(token);
//            User to = userService.getUserById(toId);
//
//            if(from.getBalance() >= amount) {
//                userService.deleteUserById(from.getId());
//                userService.deleteUserById(to.getId());
//
//                from.setBalance(from.getBalance() - amount);
//                to.setBalance(to.getBalance() + amount);
//
//                userService.registerUser(from);
//                userService.registerUser(to);
//
//                return paymentsRepository.save(new Payment(from.getId(), to.getId(), amount, new Date()));
//            }
//        }
//
//        return null;
//    }
//
//
//    public List<Payment> getAllPaymentsByToken(String token) {
//        if(paymentsRepository != null && userService.getUserByToken(token) != null)
//            return paymentsRepository.findAllByFromId(userService.getUserByToken(token).getId());
//
//        return null;
//    }
}
