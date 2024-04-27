package me.deamonet.nar.service;

import me.deamonet.nar.entity.Article;
import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.mapper.ArticleMapper;
import me.deamonet.nar.source.RSSProcessor;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleService {
    final
    ArticleMapper articleMapper;
    final
    RSSProcessor rssProcessor;


    public ArticleService(ArticleMapper articleMapper, RSSProcessor rssProcessor) {
        this.articleMapper = articleMapper;
        this.rssProcessor = rssProcessor;
    }

    public Article get(Integer id) {
        Article article = articleMapper.select(id);
        article.setRead(true);
        articleMapper.read(article.getId());
        return article;
    }

    public Integer getTotalNumber(int uid, String type) {
        switch (type) {
            case "new":
                System.out.println("new");
                return articleMapper.selectTotalNumber(uid, false);
            case "read":
                System.out.println("read");
                return articleMapper.selectTotalNumber(uid, true);
            default:
                System.out.println("all");
                return articleMapper.selectTotalNumber(uid, null);
        }

    }

    public List<Article> getArticles(Integer uid, String type, Integer offset, Integer pageSize) {
        switch (type) {
            case "new":
                return articleMapper.selectPage(uid, false, offset, pageSize);
            case "read":
                return articleMapper.selectPage(uid, true, offset, pageSize);
            default:
                return articleMapper.selectPage(uid, null, offset, pageSize);
        }

    }

    @Transactional
    public void addArticles(Integer uid, Integer sourceid, List<Article> articleList) {
        if (articleList.isEmpty()) {
            return;
        }
        articleList.forEach(article -> {
            article.setUid(uid);
            article.setSourceid(sourceid);
        });
        for (Article article : articleList) {
            if (Objects.isNull(article.getAuthor())) {
                article.setAuthor("");
            }
            if (Objects.isNull(article.getCreator())) {
                article.setCreator("");
            }
            if (Objects.isNull(article.getGuid())) {
                article.setGuid("");
            }
            if (Objects.isNull(article.getTitle())) {
                article.setTitle("");
            }
            if (Objects.isNull(article.getContent())) {
                article.setContent("");
            }
        }
        articleMapper.insertList(articleList);
    }

    public void getUpdateArticle(ArticleSource articleSource) throws DocumentException, ParseException {
        String xml = rssProcessor.getUpdate(articleSource.getFeed());
        List<Article> articleList = rssProcessor.parseXML(xml);
        List<Article> articleFilterList = new LinkedList<>();
        for (Article article: articleList){
            if (Objects.isNull(articleMapper.selectTitle(articleSource.getUid(), article.getTitle()))){
                articleFilterList.add(article);
            }
        }
        addArticles(articleSource.getUid(), articleSource.getId(), articleFilterList);
    }

    public List<Article> search(Integer uid, String searchValue) {
        return articleMapper.search(uid, searchValue);
    }
}