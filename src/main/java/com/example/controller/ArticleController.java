package com.example.controller;


import com.example.entity.Article;
import com.example.entity.Comment;
import com.example.entity.User;
import com.example.response.RestResponse;
import com.example.service.ArticleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/feed")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/add-new-article", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResponse<Article> addNewArticle(@RequestParam("email") String email, @RequestParam("title") String title,
                                              @RequestParam("content") String content, @RequestParam("url") String url) {
        User user = userService.getUserByEmail(email);
        Article article;

        if (user != null) {
            article = articleService.addNewArticle(new Article(title, content, url, user));

            if(article == null)
                return new RestResponse<>(null, "ARTICLE PROBLEM", 14);

            return new RestResponse<Article>(article, "ALL RIGHT", 0);
        }

        return new RestResponse<>(null, "USER PROBLEM", 1);
    }


    @RequestMapping(value = "/update-article", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Article> updateArticle(@RequestParam("id") Integer id, @RequestParam("what-change") String whatChange,
                                               @RequestParam("content-change") String contentChange) {
        Article article = articleService.updateArticle(id, whatChange, contentChange);

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "/delete-article", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void deleteArticle(@RequestParam("id") Integer id) {
        articleService.deleteArticle(id);
    }


    @RequestMapping(value = "/get-article", method = RequestMethod.GET)
    public RestResponse<Article> getArticle(@RequestParam("id") Integer id) {
        Article article = articleService.getArticle(id);

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "is-liked", method = RequestMethod.GET)
    public RestResponse<Boolean> isLiked(@RequestParam("id") Integer id) {
        boolean isLiked = articleService.isLiked(id);

        if(!isLiked)
            return new RestResponse<Boolean>(false, "NOT LIKED", 0);

        return new RestResponse<Boolean>(true, "LIKED", 0);
    }


    @RequestMapping(value = "add-like", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Article> addLike(@RequestParam("id") Integer id) {
        Article article = articleService.addLike(id);

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "remove-like", method = {RequestMethod.GET, RequestMethod.DELETE})
    public RestResponse<Article> removeLike(@RequestParam("id") Integer id) {
        Article article = articleService.removeLike(id);

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "add-comment", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Article> addComment(@RequestParam("id") Integer id, @RequestParam("user-id") Integer userId,
                                            @RequestParam("content") String content) {
        User user = userService.getUserById(userId);
        Article article = articleService.addComment(id, new Comment(user, content));

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "remove-comment", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Article> removeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
        Article article = articleService.removeComment(id, commentId);

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "update-comment", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Article> updateComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId,
                                               @RequestParam("content") String content){
        User user = articleService.getCommentById(id).getUser();
        Article article = articleService.updateComment(id, commentId, new Comment(user, content));

        if(article == null)
            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);

        return new RestResponse<Article>(article, "ALL RIGHT", 0);
    }


    @RequestMapping(value = "add-like-comment", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Comment> addLikeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
        Comment comment = articleService.addLikeComment(id, commentId);

        if(comment == null)
            return new RestResponse<>(null, "COMMENT PROBLEM", 15);

        return new RestResponse<Comment>(comment , "ALL RIGHT", 0);
    }


    @RequestMapping(value = "is-liked-comment", method = RequestMethod.GET)
    public RestResponse<Boolean> isLikedComment (@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
        boolean isLiked = articleService.isLikedComment(id, commentId);

        if(!isLiked)
            return new RestResponse<Boolean>(false, "NOT LIKED", 0);

        return new RestResponse<Boolean>(true, "LIKED", 0);
    }


    @RequestMapping(value = "remove-like-comment", method = {RequestMethod.GET, RequestMethod.PUT})
    public RestResponse<Comment> removeLikeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
        Comment comment = articleService.removeLikeComment(id, commentId);

        if(comment == null)
            return new RestResponse<>(null, "COMMENT PROBLEM", 15);

        return new RestResponse<Comment>(comment , "ALL RIGHT", 0);
    }
}
