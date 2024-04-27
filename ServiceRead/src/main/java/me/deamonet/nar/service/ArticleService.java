package me.deamonet.nar.service.implement;

import me.deamonet.nar.entity.Article;
import me.deamonet.nar.mapper.ArticleMapper;
import me.deamonet.nar.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImplement implements ArticleService {
    @Resource
    ArticleMapper articleMapper;

    public Article getArticleById(Integer id){
        return articleMapper.selectArticleById(id);
    }
    public List<Article> getArticleByPage(Integer offset, Integer pageSize){
        return articleMapper.selectArticleByPage(offset, pageSize);
    }
    public List<Article> getArticleByRead(Integer offset, Integer pageSize){
        return articleMapper.selectArticleByRead(false, offset, pageSize);
    }
    public List<Article> getArticleBySearch(String searchValue, Integer offset, Integer pageSize){
        return articleMapper.selectArticleBySearch(searchValue, offset, pageSize);
    }
    public Integer addArticle(Article article){
        return articleMapper.insertArticle(article);
    }
    public Integer deleteArticle(Integer id){
        return articleMapper.deleteArticleById(id);
    }
    public Integer editArticle(Article article){
        return articleMapper.updateArticle(article);
    }
}
