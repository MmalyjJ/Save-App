package com.example.newsApi;


import com.example.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
public class NewsParser {
    private static  String SEARCH_URL;

    public List<News> parseNews() {
        List<News> newsList = new ArrayList<>();

        SEARCH_URL = "https://seekingalpha.com/market-news";

        Document document = null;

        try {
            document = (Document) Jsoup.connect(SEARCH_URL).get();
        } catch (IOException e) { e.printStackTrace(); }


        Elements elements = document.select("a");

        for (Element element : elements) {
            String link = element.absUrl("href");

            if(link.contains("news") && !link.contains("market") && !link.contains("earnings") && ! link.contains("dividend"))
                getKeyPoints(link);
        }

//        String[] linksArray = new String[elements.size()];
//
//        for (int i = 0; i < elements.size(); i++)
//            linksArray[i] = elements.get(i).absUrl("href");
//
//
//        //Получение уникальных ссылок
//        Set<String> links = new TreeSet<>(Arrays.asList(linksArray));
//
//        for (String link : links) {
//            News news = new News();
//            // Выборка правильных URL-ссылок
//            if(!link.contains("20") || link.contains("peacocktv.com") || link.contains("elections"))
//                continue;
//
//            news.setTitle(getTitle(link));
//            news.setKeyPoints(getKeyPoints(link));
//            news.setPublishedAt(getPublishedAt(link));
//
//            // Елси нет основного содержания новости по ссылке -> не добавлять
//            if(!news.getKeyPoints().isEmpty() || news.getKeyPoints() != null)
//                newsList.add(news);
//        }
        return newsList;
    }


    public List<String> getKeyPoints(String link) {
        List<String> keyPoints = new ArrayList<>();

        Document document = null;

        try {
            document = (Document) Jsoup.connect(link).get();
        } catch (IOException e) { e.printStackTrace(); }

        Elements contentContainer = document.getElementsByAttributeValue("data-test-id", "content-container");

        System.out.println(contentContainer.text() + "\n");

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
