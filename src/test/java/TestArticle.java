import me.deamonet.nar.NoteAndReadApplication;
import me.deamonet.nar.controller.ArticleController;
import me.deamonet.nar.mapper.ArticleMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={NoteAndReadApplication.class})
public class TestArticle {
    @Autowired
    ArticleController controller;

    @Autowired
    ArticleMapper mapper;

    @ParameterizedTest
    @ValueSource(strings={"new", "read", "all"})
    public void totalNumber(String type){
        int number1 = controller.getTotalNumber(5, type);
        int number2 ;
        switch(type){
            case "new":
                number2 = mapper.selectTotalNumber(5, false);
            case "read":
                number2 = mapper.selectTotalNumber(5, true);
            default:
                number2 = mapper.selectTotalNumber(5, null);
        }
        System.out.println(number1);
        System.out.println(number2);
        assert number1 == number2;
    }
}
