package me.deamonet.nar.source;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.Article;
import me.deamonet.nar.entity.ArticleSource;
import org.dom4j.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RSSProcessor {
    /*
    send a rss request and interpret the rss xml
     */
    public static String pattern = "EEE, dd MMM yyyy HH:mm:ss Z";

    @Resource
    RedisTemplate<String, Integer> redisTemplate;

    public String getUpdate(String feed) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(feed, String.class);
        return responseEntity.getBody();
    }

    public List<Article> parseXML(String rssXML) throws DocumentException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
        Document document = DocumentHelper.parseText(rssXML);
        Element root = document.getRootElement();
        Element channel = root.element("channel");
        Date publishDate = format.parse(channel.elementText("pubDate")); // sometimes pubDate in channel, not in item
        List<Article> articleList = new LinkedList<>();
        for (Iterator<Element> i = channel.elementIterator(); i.hasNext(); ) {
            Element el = i.next();
            if (el.getName().equals("item")) {
                List<Node> nodes = el.selectNodes("./category");
                Article article = new Article(
                        el.elementText("link"),
                        el.elementText("title"),
                        el.elementText("description"),
                        el.elementText("author"), // some sites use author, some sites use creator, maybe there is a difference.
                        el.elementText("creator"),
                        el.elementText("guid") == null ? null : el.elementText("guid"),
                        el.elementText("pubDate") == null ? publishDate : format.parse(el.elementText("pubDate")),
                        nodes.size() == 0 ? Collections.emptyList() : nodes.stream().map(Node::getText).collect(Collectors.toList())
                );
                articleList.add(article);
            }
        }
        return articleList;
    }

    public ArticleSource parseXMLForSource(String rssXML) throws DocumentException {
        Document document = DocumentHelper.parseText(rssXML);
        Element root = document.getRootElement();
        Element channel = root.element("channel");
        String name = channel.elementText("title");
        return new ArticleSource(name);
    }


    public boolean isUpdated(String xml){
        String source = String.valueOf(xml.hashCode());
        Integer articleId = redisTemplate.opsForValue().get(source);
        if (Objects.isNull(articleId)){
            redisTemplate.opsForValue().set(source, 1);
            return true;
        } else {
            return false;
        }
    }

    public List<Article> isReceived(List<Article> articleList){
        Date currentTime = new Date();
        List<Article> result = new LinkedList<Article>();
        for (Article article : articleList){
            if (article.getFirstPublish().after(currentTime)){
                result.add(article);
            }
        }
        return result;
    }
}
