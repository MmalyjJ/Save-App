package com.example.service;


import com.example.entity.Article;
import com.example.entity.Comment;
import com.example.repo.ArticleRepository;
import com.example.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;


    /* ------ МЕТОДЫ ДЛЯ РАБОТЫ СО СТАТЬЯМИ ------ */

    //Добавление статей
    public Article addNewArticle(Article article) {
        if(articleRepository != null && !articleRepository.exists(Example.of(article)))
            return articleRepository.save(article);

        return null;
    }


    //Удаление статей
    public void deleteArticle(Integer id) {
        if(articleRepository != null && articleRepository.getOne(id) != null)
            articleRepository.deleteById(id);
    }


    //УИзменить статью
    public Article updateArticle(Integer id, String whatChange, String contentChange) {
        if(articleRepository != null && articleRepository.getOne(id) != null) {
            Article article = articleRepository.getOne(id);

            switch (whatChange) {
                case "title":
                    article.setTitle(contentChange);
                    return articleRepository.save(article);

                case "content":
                    article.setContent(contentChange);
                    return articleRepository.save(article);

                default:
                    return null;
            }
        }

        return null;
    }


    //Получить статью по id
    public Article getArticle(Integer id) {
        if(articleRepository != null && articleRepository.getOne(id) != null)
            return articleRepository.getOne(id);

        return null;
    }


    // Получить все статьи пользователей
    public List<Article> getAllArticles() {
        if(articleRepository != null)
            return articleRepository.findAll();

        return null;
    }


    /* ------ МЕТОДЫ ДЛЯ РАБТО С ЛАЙКАМИ ------ */

    // Пролайкана ли статья
    public boolean isLiked(Integer id) {
        if(articleRepository != null && articleRepository.getOne(id) != null)
            return articleRepository.getOne(id).isLiked();

        return false;
    }


    // Добавление лайка
    public Article addLike(Integer id) {
        if(articleRepository != null && articleRepository.getOne(id) != null) {
            Article article = articleRepository.getOne(id);

            article.setLikes(article.getLikes() + 1);

            return articleRepository.save(article);
        }

        return null;
    }


    // Убрать лайк
    public Article removeLike(Integer id) {
        if(articleRepository != null && articleRepository.getOne(id) != null) {
            Article article = articleRepository.getOne(id);

            if(article.getCommentsCount() > 0) {
                article.setLikes(article.getLikes() - 1);

                return articleRepository.save(article);
            }
        }

        return null;
    }


    /* ------ МЕТОД ДЛЯ РАБОТЫ С КОММЕНТАРИИ ------ */

    // Добавление комментария
    public Article addComment(Integer articleId, Comment comment) {
        if(articleRepository != null && articleRepository.getOne(articleId) != null) {
            if(!commentRepository.exists(Example.of(comment))) {
                Article article = articleRepository.getOne(articleId);
                List<Comment> comments = article.getComments();

                comments.add(comment);

                article.setComments(comments);

                return articleRepository.save(article);
            }
        }

        return null;
    }


    // Удаление комментария
    public Article removeComment(Integer articleId, Integer commentId) {
        if(articleRepository != null && articleRepository.getOne(articleId) != null) {
            if(commentRepository.getOne(commentId) != null) {
                Article article = articleRepository.getOne(articleId);
                List<Comment> comments = article.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0) {
                    comments.remove(comment);

                    article.setComments(comments);

                    return articleRepository.save(article);
                }
            }
        }

        return null;
    }


    public Comment getCommentById(Integer id){
        if(commentRepository != null && commentRepository.getOne(id) != null)
            return commentRepository.getOne(id);

        return null;
    }


    // Изменить коммент
    public Article updateComment(Integer articleId, Integer commentId , Comment commentChanged) {
        if(articleRepository != null &&  articleRepository.getOne(articleId) != null) {
            if (commentRepository != null && commentRepository.getOne(commentId) != null) {
                Article article = articleRepository.getOne(articleId);
                Comment comment = commentRepository.getOne(commentId);
                List<Comment> comments = article.getComments();

                int index = comments.indexOf(comment);

                if (index >= 0)
                    comments.set(index, commentChanged);

                article.setComments(comments);

                articleRepository.save(article);
            }
        }

        return null;
    }


    // Есть ли лайк для коммента
    public boolean isLikedComment(Integer articleId, Integer commentId) {
        if(articleRepository != null && articleRepository.getOne(articleId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                Article article = articleRepository.getOne(articleId);
                List<Comment> comments = article.getComments();
                Comment comment = commentRepository.getOne(commentId);

                int index = comments.indexOf(comment);

                if (index >= 0)
                    return comments.get(index).isLiked();
            }
        }

        return false;
    }


    // Добавить лайк
    public Comment addLikeComment(Integer articleId, Integer commentId) {
        if(articleRepository != null && articleRepository.getOne(articleId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                Article article = articleRepository.getOne(articleId);
                List<Comment> comments = article.getComments();
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
    public Comment removeLikeComment(Integer articleId, Integer commentId) {
        if(articleRepository != null && articleRepository.getOne(articleId) != null) {
            if(commentRepository != null && commentRepository.getOne(commentId) != null) {
                Article article = articleRepository.getOne(articleId);
                List<Comment> comments = article.getComments();
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
