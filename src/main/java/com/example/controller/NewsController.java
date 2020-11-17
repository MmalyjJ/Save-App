package com.example.controller;


import com.example.entity.News;
import com.example.newsApi.NewsAPI;
import com.example.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsAPI newsAPI;


    @RequestMapping(value = "/get-news-by-category", method = RequestMethod.GET)
    public RestResponse<List<News>> getNewsByCategory(@RequestParam("category") String category) {
        List<News> news = newsAPI.getNewsByCategory(category);

        if(news == null)
            return new RestResponse<>(null, "NEWS PROBLEM", 12);

        return new RestResponse<List<News>>(news, "ALL RIGHT", 0);
    }
}
