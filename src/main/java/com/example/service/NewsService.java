package com.example.service;


import com.example.entity.Article;
import com.example.entity.Comment;
import com.example.entity.News;
import com.example.newsApi.NewsAPI;
import com.example.repo.CommentRepository;
import com.example.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsService {
    @Autowired
    NewsAPI newsAPI;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CommentRepository commentRepository;



    /* ------ МЕТОДЫ ДЛЯ РАБОТЫ С НОВОСТЯМИ ------ */
    public List<News> getNews(String category) {
        if(newsRepository != null) {
            List<News> newsList = newsAPI.getNewsByCategory(category);

            for (News news : newsList) {
                if(!newsRepository.exists(Example.of(news)))
                    newsRepository.save(news);
            }

            return newsList;
        }

        return null;
    }


    /* ------ МЕТОДЫ ДЛЯ РАБОТЫ С ЛАЙКАМИ ------ */

    // Пролайкана ли новость
    public boolean isLiked(Integer id) {
        if(newsRepository != null && newsRepository.getOne(id) != null)
            return newsRepository.getOne(id).isLiked();

        return false;
    }


    // Добавить лайк
    public News addLike(Integer id) {
        if(newsRepository != null && newsRepository.getOne(id) == null) {
            News news = newsRepository.getOne(id);

            news.setLikes(news.getLikes() + 1);

            return newsRepository.save(news);
        }

        return null;
    }


    // Уалить лайка
    public News removeLike(Integer id) {
        if(newsRepository != null && newsRepository.getOne(id) == null) {
            News news = newsRepository.getOne(id);

            news.setLikes(news.getLikes() - 1);

            return newsRepository.save(news);
        }

        return null;
    }


    /* ------ МЕТОДЫ ДЛЯ РАБОЫ С КОММЕНТАРИЯМИ ------ */

    // Добавить коммент
    public News addComment(Integer newsId, Comment comment) {
        if(newsRepository != null && newsRepository.getOne(newsId) != null) {
            if(!commentRepository.exists(Example.of(comment))) {
                News news = newsRepository.getOne(newsId);
                List<Comment> comments = news.getComments();

                comments.add(comment);

                news.setComments(comments);

                return newsRepository.save(news);
            }
        }

        return null;
    }


    // Убрать коммент
    public News removeComment(Integer newsId, Integer commentId) {
        if(newsRepository != null && newsRepository.getOne(newsId) != null) {
            if(commentRepository.getOne(commentId) != null) {
                News news = newsRepository.getOne(newsId);
                List<Comment> comments = news.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0) {
                    comments.remove(comment);

                    news.setComments(comments);

                    return newsRepository.save(news);
                }
            }
        }

        return null;
    }


    //Изменить коммент
    public News updateComment(Integer newsId, Integer commentId , Comment commentChanged) {
        if(newsRepository != null &&  newsRepository.getOne(newsId) != null) {
            if (commentRepository != null && commentRepository.getOne(commentId) != null) {
                News news = newsRepository.getOne(newsId);
                Comment comment = commentRepository.getOne(commentId);
                List<Comment> comments = news.getComments();

                int index = comments.indexOf(comment);

                if (index >= 0)
                    comments.set(index, commentChanged);

                news.setComments(comments);

                newsRepository.save(news);
            }
        }

        return null;
    }


    public Comment getCommentById(Integer id){
        if(commentRepository != null && commentRepository.getOne(id) != null)
            return commentRepository.getOne(id);

        return null;
    }


    // Есть ли лайк для коммента
    public boolean isLikedComment(Integer newsId, Integer commentId) {
        if(newsRepository != null && newsRepository.getOne(newsId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                News news = newsRepository.getOne(newsId);
                List<Comment> comments = news.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0)
                    return comments.get(index).isLiked();
            }
        }

        return false;
    }


    // Добавить лайк
    public Comment addLikeComment(Integer newsId, Integer commentId) {
        if(newsRepository != null && newsRepository.getOne(newsId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                News news = newsRepository.getOne(newsId);
                List<Comment> comments = news.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0) {
                    comment.setLikes(comment.getLikes() + 1);

                    commentRepository.save(comment);
                }
            }
        }

        return null;
    }


    // Убрать лайк
    public Comment removeLikeComment(Integer newsId, Integer commentId) {
        if(newsRepository != null && newsRepository.getOne(newsId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                News news = newsRepository.getOne(newsId);
                List<Comment> comments = news.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0) {
                    comment.setLikes(comment.getLikes() - 1);

                    commentRepository.save(comment);
                }
            }
        }

        return null;
    }
}
