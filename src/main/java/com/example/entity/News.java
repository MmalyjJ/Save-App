package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
//@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String publishedAt;

    private String title;

    private String about;

    @ElementCollection
    private List<String> keyPoints;

    private boolean isLiked;

    private int likes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, mappedBy = "comments")
//    @JoinTable(
//            name = "news_comments",
//            joinColumns = @JoinColumn(name = "news_id"),
//            inverseJoinColumns = @JoinColumn(name = "comment_id")
//    )
    private List<Comment> comments;

    private int commentsCount;
}
