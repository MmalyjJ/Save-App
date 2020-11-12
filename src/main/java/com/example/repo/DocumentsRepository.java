package com.example.repo;


import com.example.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Integer> {
    List<Documents> findById(String id);
}
