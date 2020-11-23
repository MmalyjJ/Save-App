package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
//@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    @Id
//    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @Column(name = "title")
    private String title;

//    @Column(name = "content")
    private String content;

//    @Column(name = "isLiked")
    private boolean isLiked;

//    @Column(name = "likes")
    private int likes;

//    @Column(name = "date")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy = "users")
//    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy = "comments")
//    @JoinTable(
//            name = "articles_comments",
//            joinColumns = @JoinColumn(name = "article_id"),
//            inverseJoinColumns = @JoinColumn(name = "comment_id")
//    )
    private List<Comment> comments;

    @Column(name = "commentsCount")
    private int commentsCount;


    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.isLiked = false;
        this.likes = 0;
        this.comments = new ArrayList<>();
        this.commentsCount = 0;
        this.user = user;
    }
}
