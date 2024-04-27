package me.deamonet.nar.controller;


import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.Article;
import me.deamonet.nar.service.ArticleService;
import me.deamonet.nar.source.RSSSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/{uid:\\d+}/article")
public class ArticleController {
    final
    ArticleService articleService;
    @Autowired
    RSSSubscriber rssSubscriber;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/total")
    public Integer getTotalNumber(@PathVariable("uid") int uid,
                                  @RequestParam("type") String type) {
        return articleService.getTotalNumber(uid, type);
    }

    @GetMapping("/page")
    public List<Article> get(@PathVariable("uid") int uid,
                             @RequestParam("type") String type,
                             @RequestParam("offset") int offset,
                             @RequestParam("page_size") int pageSize) {
        log.info("user " + uid + " get one new page article");
        return articleService.getArticles(uid, type, offset, pageSize);
    }

    @GetMapping("/{id:\\d+}")
    public Article get(@PathVariable int uid,
                       @PathVariable("id") int id) {
        return articleService.get(id);
    }

    @PostMapping("/update")
    public Boolean update(@PathVariable("uid") int uid) {
        log.info("update user " + uid);
        return rssSubscriber.updateRSS(uid);
    }
}