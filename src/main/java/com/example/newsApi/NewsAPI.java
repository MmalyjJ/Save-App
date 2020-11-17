package com.example.newsApi;


import com.example.entity.News;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Service
public class NewsAPI {
    private static final String URL_ADDRESS = "http://newsapi.org/v2/top-headlines?category=";
    private static final String API_KEY = "&apiKey=a8ab51cc7ddb41fc9e63126e99b106bd";


    public List<News> getNewsByCategory(String category)  {
        News news = new News();
        List<News> newsList = new ArrayList<News>();

        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        String tempString;

        try {
            url = new URL(URL_ADDRESS + category + API_KEY);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        try {
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((tempString = bufferedReader.readLine()) != null)
                content.append(tempString + "\n");

        } catch (IOException e) { e.printStackTrace(); }

        System.out.println(new JSONObject(content.toString()));

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject article = new JSONObject();

        JSONArray articles = jsonObject.getJSONArray("articles");

        for (int i = 0; i < articles.length(); i++) {
            article = articles.getJSONObject(i);

            news.setPublishedAt(article.getString("publishedAt"));
            news.setAuthor(article.getString("author"));
            news.setUrlToImage(article.getString("urlToImage"));
            news.setDescription(article.getString("description"));
            news.setSourceName(article.getString("sourceName"));
            news.setTitle(article.getString("title"));
            news.setUrl(article.getString("url"));
            news.setContent(article.getString("content"));

            newsList.add(news);
        }

        return newsList;
    }
}
