package com.example.parser;


import com.example.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


@Service
public class NewsParser {
    private static  String SEARCH_URL;

    public List<News> parseNews() {
        List<News> newsList = new ArrayList<>();

        SEARCH_URL = "https://www.cnbc.com/latest/";

        Document document = null;

        try {
            document = (Document) Jsoup.connect(SEARCH_URL).get();
        } catch (IOException e) { e.printStackTrace(); }


        Elements elements = document.select("a");
        String[] linksArray = new String[elements.size()];

        for (int i = 0; i < elements.size(); i++)
            linksArray[i] = elements.get(i).absUrl("href");


        //Получение уникальных ссылок
        Set<String> links = new TreeSet<>(Arrays.asList(linksArray));

        for (String link : links) {
            News news = new News();
            // Выборка правильных URL-ссылок
            if(!link.contains("20") || link.contains("peacocktv.com") || link.contains("elections"))
                continue;

            news.setTitle(getTitle(link));
            news.setKeyPoints(getKeyPoints(link));
            news.setPublishedAt(getPublishedAt(link));

            // Елси нет основного содержания новости по ссылке -> не добавлять
            if(!news.getKeyPoints().isEmpty() || news.getKeyPoints() != null)
                newsList.add(news);
        }

        return newsList;
    }


    public List<String> getKeyPoints(String link) {
        List<String> keyPoints = new ArrayList<>();

        Document document = null;

        try {
            document = (Document) Jsoup.connect(link).get();
        } catch (IOException e) { e.printStackTrace(); }

        Elements group = document.select("div.group > ul");

        String[] keyPointsSplited = group.text().split("[.]");

        Collections.addAll(keyPoints, keyPointsSplited);

        return keyPoints;
    }


    public String getPublishedAt(String link) {
        Document document = null;

        try {
            document = (Document) Jsoup.connect(link).get();
        } catch (IOException e) { e.printStackTrace(); }

        Elements date = document.getElementsByAttributeValue("data-testid", "published-timestamp");
        Elements time = document.getElementsByAttributeValue("class", "ArticleHeader-datetimeDivider");

        return date.text() + time.text();
    }


    public String getTitle(String link) {
        Document document = null;

        try {
            document = (Document) Jsoup.connect(link).get();
        } catch (IOException e) { e.printStackTrace(); }

        Elements title = document.getElementsByAttributeValue("class", "ArticleHeader-headline");

        return title.text();
    }
}
