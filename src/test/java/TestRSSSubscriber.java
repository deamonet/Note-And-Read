import me.deamonet.nar.NoteAndReadApplication;
import me.deamonet.nar.entity.ArticleSource;
import me.deamonet.nar.service.ArticleService;
import me.deamonet.nar.source.RSSSubscriber;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;


@SpringBootTest(classes={NoteAndReadApplication.class})
public class TestRSSSubscriber {
    @Autowired
    RSSSubscriber subscriber;
    @Autowired
    ArticleService service;

    @ParameterizedTest
    @ValueSource(ints = { 7 })
    public void testGetUpdate(int uid){
        int total1 = service.getTotalNumber(uid, "all");
        subscriber.updateRSS(uid);
        int total2 = service.getTotalNumber(uid, "all");
        assert  (total2 > total1);
    }

    @Test
    public void testSourceUpdate() throws DocumentException, ParseException {
        service.getUpdateArticle(new ArticleSource(1, 7, "奇客", "https://www.solidot.org/index.rss"));
    }
}
