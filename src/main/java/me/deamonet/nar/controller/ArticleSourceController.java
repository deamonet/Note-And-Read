package me.deamonet.nar.controller;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.service.ArticleService;
import me.deamonet.nar.service.ArticleSourceService;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/{uid:\\d+}/source")
public class ArticleSourceController {

    final
    ArticleSourceService articleSourceService;
    final
    ArticleService articleService;

    public ArticleSourceController(ArticleSourceService articleSourceService, ArticleService articleService) {
        this.articleSourceService = articleSourceService;
        this.articleService = articleService;
    }

    @GetMapping("/total")
    public Integer getTotalNumber(@PathVariable("uid") int uid, @RequestParam("type") String type) {
        return articleService.getTotalNumber(uid, type);
    }

    @GetMapping("/all")
    public List<ArticleSource> getAll(@PathVariable("uid") int uid) {
        return articleSourceService.getAll(uid);
    }

    @PostMapping()
    public Integer add(@PathVariable int uid,
                       @RequestBody ArticleSource articleSource) throws DocumentException {
        log.info("user " + uid + "add a source");
        articleSource.setUid(uid);
        return articleSourceService.add(articleSource);
    }


    @DeleteMapping("/{sourceid:\\d+}")
    public Integer delete(@PathVariable("uid") int uid,
            @PathVariable("sourceid") int sourceid){
        log.info("user" + uid + "delete a source");
        return articleSourceService.delete(sourceid);
    }
}
