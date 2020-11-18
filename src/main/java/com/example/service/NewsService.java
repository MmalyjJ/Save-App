package com.example.service;


import com.example.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;


    public boolean isLiked(Integer id) {
        if(newsRepository != null && newsRepository.getOne(id) != null)
            return newsRepository.getOne(id).isLiked();

        return false;
    }
}
