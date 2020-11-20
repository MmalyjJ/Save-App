package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


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

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "category")
    private String category;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "news_comments",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments;

    private int commentsCount;
}
