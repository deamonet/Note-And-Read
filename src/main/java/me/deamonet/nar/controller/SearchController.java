package me.deamonet.nar.controller;

import me.deamonet.nar.service.ArticleService;
import me.deamonet.nar.service.NoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{uid:\\d+}/search")
public class SearchController {
    final
    ArticleService articleService;
    final
    NoteService noteService;

    public SearchController(ArticleService articleService, NoteService noteService) {
        this.articleService = articleService;
        this.noteService = noteService;
    }


    @GetMapping()
    public Object searchArticles(@PathVariable("uid") int uid,
                                 @RequestParam("search_value") String searchValue,
                                 @RequestParam("search_range") String searchRange) {
        if ("note".equals(searchRange)) {
            return noteService.searchList(uid, searchValue);
        }
        return articleService.search(uid, searchValue);
    }
}