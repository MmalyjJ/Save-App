package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)//, mappedBy = "users")
//    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "content")
    private String content;

//    @Column(name = "date")
    private Date date;

//    @Column(name = "isLiked")
    private boolean isLiked;

//    @Column(name = "likes")
    private int likes;


    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
        this.date = new Date();
        this.isLiked = false;
        this.likes = 0;
    }
}
