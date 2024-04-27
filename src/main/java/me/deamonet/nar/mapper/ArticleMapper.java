package me.deamonet.nar.mapper;


import me.deamonet.nar.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    Integer selectTotalNumber(@Param("uid") Integer uid, @Param("is_read") Boolean isRead);

    Article select(Integer id);
    Article selectTitle(@Param("uid") Integer uid,@Param("title") String title);

    List<Article> selectPage(@Param("uid") Integer uid, @Param("is_read") Boolean isRead, @Param("offset") Integer offset, @Param("page_size") Integer pageSize);

    List<Article> search(@Param("uid") Integer uid, @Param("search_value") String searchValue);

    Integer insertList(@Param("article_list") List<Article> articleList);
    void read(Integer id);
}