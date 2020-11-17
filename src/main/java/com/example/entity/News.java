package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "publishedAt")
    private String publishedAt;

    @Column(name = "author")
    private String author;

    @Column(name = "urlToImage")
    private String urlToImage;

    @Column(name = "description")
    private String description;

    @Column(name = "sourceName")
    private String sourceName;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "isLiked")
    private boolean isLiked;

    @Column(name = "likes")
    private int likes;
}
