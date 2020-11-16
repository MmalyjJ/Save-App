package com.example.service;


import com.example.entity.Documents;
import com.example.repo.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DocumentService {
    @Autowired
    DocumentsRepository documentsRepository;


    public Documents registerDocuments(Documents documents) {
        if(documentsRepository != null)
            return documentsRepository.save(documents);

        return null;
    }


    public Optional<Documents> getAllDocumentsById(Integer id) {
        if(documentsRepository != null)
            return documentsRepository.findById(id);

        return null;
    }
}
