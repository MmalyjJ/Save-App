package com.example.controller;


import com.example.entity.News;
import com.example.newsApi.NewsParser;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {
//    @Autowired
//    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    NewsParser newsParser;


    @RequestMapping(value = "/get-parsed-news", method = RequestMethod.GET)
    public List<News> getNewsParsed() {
        return newsParser.parseNews();
    }

//    @RequestMapping(value = "/get-news", method = RequestMethod.GET)
//    public void getNewsByCategory(@RequestParam("category") String category) throws IOException, InterruptedException {
////        List<News> news = newsService.getAllNewsByCategory(category);
////
////        if(news == null)
////            return new RestResponse<>(null, "NEWS PROBLEM", 12);
////
////        return new RestResponse<List<News>>(news, "ALL RIGHT", 0);
//        newsAPI.getNewsByCategory("");
//    }
//
//
//    @RequestMapping(value = "/get-news-from-db", method = RequestMethod.GET)
//    public RestResponse<List<News>> getNewsByCategoryFromDB(@RequestParam("category") String category) {
//        List<News> news = newsService.getAllNewsByCategoryFromDB(category);
//
//        if(news == null)
//            return new RestResponse<>(null, "NEWS PROBLEM", 12);
//
//        return new RestResponse<List<News>>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/get-news-by-id", method = RequestMethod.GET)
//    public News getNewsById(@RequestParam("id") Integer id) {
////        News news = newsService.getNewsById(id);
////
////        if(news == null)
////            return new RestResponse<>(null, "NEWS PROBLEM", 12);
////
////        return new RestResponse<News>(news, "ALL RIGHT", 0);
//        return newsService.getNewsById(id);
//    }
//
//
//    @RequestMapping(value = "/is-liked", method = RequestMethod.GET)
//    public RestResponse<Boolean> getIsLiked(@RequestParam("id") Integer id) {
//        Boolean isLiked = newsService.isLiked(id);
//
//        if(!isLiked)
//            return new RestResponse<Boolean>(false, "NEWS IS NOT LIKED", 0);
//
//        return new RestResponse<Boolean>(true, "NEWS IS LIKED", 0);
//    }
//
//
//    @RequestMapping(value = "/add-like", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<News> addLike(@RequestParam("id") Integer id) {
//        News news = newsService.addLike(id);
//
//        if(news == null)
//            return new RestResponse<>(null , "NEWS PROBLEM", 14);
//
//        return new RestResponse<News>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/remove-like", method = {RequestMethod.GET, RequestMethod.DELETE})
//    public RestResponse<News> removeLike(@RequestParam("id") Integer id) {
//        News news = newsService.removeLike(id);
//
//        if(news == null)
//            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);
//
//        return new RestResponse<News>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/add-comment", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<News> addComment(@RequestParam("id") Integer id, @RequestParam("user-id") Integer userId,
//                                            @RequestParam("content") String content) {
//        News news = newsService.addComment(id, new Comment(userService.getUserById(userId), content));
//
//        if(news == null)
//            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);
//
//        return new RestResponse<News>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/remove-comment", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<News> removeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
//        News news = newsService.removeComment(id, commentId);
//
//        if(news == null)
//            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);
//
//        return new RestResponse<News>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/update-comment", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<News> updateComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId,
//                                               @RequestParam("content") String content){
//        User user = newsService.getCommentById(id).getUser();
//        News news = newsService.updateComment(id, commentId, new Comment(user, content));
//
//        if(news == null)
//            return new RestResponse<>(null , "ARTICLE PROBLEM", 14);
//
//        return new RestResponse<News>(news, "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/add-like-comment", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<Comment> addLikeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
//        Comment comment = newsService.addLikeComment(id, commentId);
//
//        if(comment == null)
//            return new RestResponse<>(null, "COMMENT PROBLEM", 15);
//
//        return new RestResponse<Comment>(comment , "ALL RIGHT", 0);
//    }
//
//
//    @RequestMapping(value = "/is-liked-comment", method = RequestMethod.GET)
//    public RestResponse<Boolean> isLikedComment (@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
//        boolean isLiked = newsService.isLikedComment(id, commentId);
//
//        if(!isLiked)
//            return new RestResponse<Boolean>(false, "NOT LIKED", 0);
//
//        return new RestResponse<Boolean>(true, "LIKED", 0);
//    }
//
//
//    @RequestMapping(value = "/remove-like-comment", method = {RequestMethod.GET, RequestMethod.PUT})
//    public RestResponse<Comment> removeLikeComment(@RequestParam("id") Integer id, @RequestParam("comment-id") Integer commentId) {
//        Comment comment = newsService.removeLikeComment(id, commentId);
//
//        if(comment == null)
//            return new RestResponse<>(null, "COMMENT PROBLEM", 15);
//
//        return new RestResponse<Comment>(comment , "ALL RIGHT", 0);
//    }
}
