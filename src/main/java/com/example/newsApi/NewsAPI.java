package com.example.newsApi;


import com.example.entity.News;
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


    public String formatContent(String content, int howMuchWords) {
        String[] contentParts = content.split(" ", howMuchWords);
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < contentParts.length; i++) {
            result.append(contentParts[i]);
        }

        System.out.println(contentParts.length);
        System.out.println(result);

        return result.toString();
    }


    public List<News> getNewsByCategory(String category)  {
        News news;
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

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject article = new JSONObject();

        JSONArray articles = jsonObject.getJSONArray("articles");


        for (int i = 0; i < articles.length(); i++) {
            article = articles.getJSONObject(i);
            news = new News();

            news.setPublishedAt(article.optString("publishedAt"));
            news.setAuthor(article.optString("author").toString());
            news.setUrlToImage(article.optString("urlToImage").toString());
            news.setDescription(formatContent(article.optString("description"), 2));
            news.setSourceName(article.getJSONObject("source").optString("sourceName"));
            news.setTitle(article.optString("title").toString());
            news.setUrl(article.optString("url").toString());
            news.setCategory(category);

            newsList.add(news);
        }

        return newsList;
    }
}
