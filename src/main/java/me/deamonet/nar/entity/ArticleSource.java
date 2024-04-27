package me.deamonet.nar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSource {
    private Integer id;
    private Integer uid;
    private String name;
    private String feed;
    private Date creatAt;
    public ArticleSource(String name) {
        this.name = name;
    }

    public ArticleSource(Integer id, Integer uid, String name, String feed) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.feed = feed;
    }
}
