package me.deamonet.nar.source;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.bean.PageSize;
import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.service.ArticleService;
import me.deamonet.nar.service.ArticleSourceService;
import me.deamonet.nar.service.UserService;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Component
public class RSSSubscriber {

    final ArticleService articleService;

    final ArticleSourceService articleSourceService;
    final
    PageSize pageSize;
    final
    UserService userService;

    public RSSSubscriber(ArticleService articleService, ArticleSourceService articleSourceService, PageSize pageSize, UserService userService) {
        this.articleService = articleService;
        this.articleSourceService = articleSourceService;
        this.pageSize = pageSize;
        this.userService = userService;
    }


    //    给指定用户更新
    public Boolean updateRSS(int uid) {
        List<ArticleSource> sourceList = articleSourceService.getAll(uid);
        if (sourceList.size() == 0) {
            return false;
        }
        for (ArticleSource source: sourceList) {
            updateRSSBySource(source);
        }
        return true;
    }

    public void updateRSSBySource(ArticleSource articleSource) {
        try {
            articleService.getUpdateArticle(articleSource);
        } catch (DocumentException | ParseException e) {
            log.error(e.getMessage(), e);
        }
    }
}
