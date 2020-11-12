package com.example.repo;


import com.example.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {
    //Получение всех платежей по ID пользователя ОТ КОГО были выполнены платежи
    List<Payment> findAllByFromToken(String token);
    List<Payment> findAllByFromEmail(String email);
}
