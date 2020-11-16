package com.example.repo;


import com.example.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByName(String name);
}
