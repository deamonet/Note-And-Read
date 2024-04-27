package me.deamonet.nar.service.implement;

import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.mapper.ArticleSourceMapper;
import me.deamonet.nar.service.ArticleSourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleSourceServiceImplement implements ArticleSourceService {

    final
    ArticleSourceMapper articleSourceMapper;

    public ArticleSourceServiceImplement(ArticleSourceMapper articleSourceMapper) {
        this.articleSourceMapper = articleSourceMapper;
    }

    @Transactional
    public Integer addArticleSource(ArticleSource articleSource){
        return articleSourceMapper.insertArticleSource(articleSource);
    }

    @Transactional
    public Integer deleteArticleSource(Integer id){
        return articleSourceMapper.deleteArticleSourceById(id);
    }

    @Transactional
    public Integer updateArticleSource(ArticleSource articleSource){
        return articleSourceMapper.updateArticleSource(articleSource);
    }
}
