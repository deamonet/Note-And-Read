package me.deamonet.nar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private Integer uid;
    private Integer sourceid; // rss source id
    private String guid;
    private String title;
    private String author;
    private String creator;
    private String content;
    private String link; // the direct url links to the article
    private Date firstPublish; // first publish time, probably not equals to the time that the rss source published
    private boolean isRead; // default false
    private String tags; // delimiter by comma

    public Article(String link, String title, String description, String author, String creator, String guid, Date date, List<String> stringList) {
        this.link = link;
        this.title = title;
        this.content = description;
        this.author = author;
        this.creator = creator;
        this.guid = guid;
        this.firstPublish = date;
        this.tags = String.join(" ", stringList);
    }

    public boolean equals(Article article){
        return this.title.equals(article.getTitle()) && this.author.equals(article.getAuthor()) && this.content.equals(article.getContent());
    }

    public int hashCode(){
        int h = 0;
        h = 31 * h + this.title.hashCode();
        h = 31 * h + this.author.hashCode();
        h = 31 * h + this.content.hashCode();
        return h;
    }
}