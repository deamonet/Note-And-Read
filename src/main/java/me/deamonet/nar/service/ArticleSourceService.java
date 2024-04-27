package me.deamonet.nar.service;

import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.mapper.ArticleSourceMapper;
import me.deamonet.nar.source.RSSProcessor;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleSourceService {
    final
    ArticleSourceMapper articleSourceMapper;

    final
    RSSProcessor rssProcessor;

    public ArticleSourceService(ArticleSourceMapper articleSourceMapper, RSSProcessor rssProcessor) {
        this.articleSourceMapper = articleSourceMapper;
        this.rssProcessor = rssProcessor;
    }

    public Integer getTotalNumber(int uid) {
        return articleSourceMapper.selectTotalNumber(uid);
    }

    public ArticleSource get(int id) {
        return articleSourceMapper.select(id);
    }

    public List<ArticleSource> getAll(int uid){
        return articleSourceMapper.selectAll(uid);
    }

    @Transactional
    public Integer add(int uid, String feedurl) throws DocumentException {
        String xml = rssProcessor.getUpdate(feedurl);
        ArticleSource articleSource = rssProcessor.parseXMLForSource(xml);
        articleSource.setFeed(feedurl);
        articleSource.setUid(uid);
        return add(articleSource);
    }

    @Transactional
    public Integer add(ArticleSource articleSource) {
        return articleSourceMapper.insert(articleSource);
    }

    @Transactional
    public Integer delete(int id) {
        return articleSourceMapper.delete(id);
    }
}
