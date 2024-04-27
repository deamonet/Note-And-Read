import me.deamonet.nar.entity.Article;
import me.deamonet.nar.source.RSSProcessor;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@ExtendWith(SpringExtension.class)
public class TestRSSProcessor {
    @Test
    public void testGetUpdate(){
        RSSProcessor rssInterpreter = new RSSProcessor();
        String feedUrl = "https://www.solidot.org/index.rss";
        String rss = rssInterpreter.getUpdate(feedUrl);
        System.out.println(rss);
        System.out.println(rss.length());
    }

    @ParameterizedTest()
    @MethodSource("rssProvider")
    public void testParseXML(String rssXML){
        RSSProcessor interpreter = new RSSProcessor();
        try {
            List<Article> articleList = interpreter.parseXML(rssXML);
            for (Article article: articleList){
                System.out.println(article);
            }
        } catch (DocumentException |ParseException e){
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }


    public static Stream<String> rssProvider() throws IOException {
        File file = org.springframework.util.ResourceUtils.getFile("classpath:rss-xml/sspai-rss.xml");
        InputStreamReader reader = new InputStreamReader(Files.newInputStream(file.toPath()));
//        ClassPathResource classPathResource = new ClassPathResource("rss-xml/dig-for-fire-rss.xml");
        BufferedReader bufferedRead = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        line = bufferedRead.readLine();
        while (!Objects.isNull(line)) {
            stringBuilder.append(line);
            System.out.println(line);
            line = bufferedRead.readLine(); // 一次读入一行数据
        }
        return Stream.of(stringBuilder.toString());
    }
}
