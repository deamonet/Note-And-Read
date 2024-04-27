package me.deamonet.nar.mapper;

import me.deamonet.nar.entity.ArticleSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleSourceMapper {
    Integer selectTotalNumber(Integer uid);
    ArticleSource select(Integer id);
    List<ArticleSource> selectAll(Integer uid);
    Integer insert(ArticleSource article);
    Integer delete(int id);
}
