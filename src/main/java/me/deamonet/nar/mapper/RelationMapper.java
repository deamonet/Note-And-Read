package me.deamonet.nar.mapper;

import me.deamonet.nar.entity.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationMapper {
    List<Relation> selectByNote(Integer source);
    List<Relation> selectByNotebook(Integer notebookid);

    Integer insert(Relation relation);

    Integer delete(@Param("source") Integer source, @Param("target") Integer target);
    Integer deleteByNote(@Param("source") Integer source, @Param("target") Integer target);
}
