package me.deamonet.nar.interpreter;

import me.deamonet.nar.entity.Article;
import org.dom4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Component
public class RSSProcessor {
    /*
    send a rss request and interpret the rss xml
     */
    public static String pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
    public String getUpdate(String feed){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(feed, String.class);
        return responseEntity.getBody();
    }
    public List<Article> parseXML(String rssXML) throws DocumentException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
        Document document = DocumentHelper.parseText(rssXML);
        Element root = document.getRootElement();
        Element channel = root.element("channel");
        List<Article> articleList = new LinkedList<>();
        for (Iterator<Element> i = channel.elementIterator(); i.hasNext();) {
            Element el = i.next();
            if (el.getName().equals("item")){
                List<Node> nodes = el.selectNodes("./category");
                Article article = new Article(
                        el.elementText("link"),
                        el.elementText("title"),
                        el.elementText("description"),
                        el.elementText("creator"),
                        el.elementText("pubDate") == null ? null : format.parse(el.elementText("pubDate")),
                        nodes.size() == 0 ? new String[0]: nodes.stream().map(Node::getText).toList().toArray(new String[0])
                );
                article.setCover(el.element("content") == null ? null : el.element("content").attributeValue("url"));
                articleList.add(article);
            }
        }
        return articleList;
    }
    public List<Article> getArticles(String feed) throws DocumentException, ParseException {
        String rssXML = getUpdate(feed);
        return parseXML(rssXML);
    }
}
