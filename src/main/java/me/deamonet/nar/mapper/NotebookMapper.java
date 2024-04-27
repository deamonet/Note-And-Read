package me.deamonet.nar.mapper;

import me.deamonet.nar.entity.Notebook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotebookMapper {
    Notebook select(Integer id);

    Integer selectTotalNumber(Integer uid);

    List<Notebook> selectAll(Integer uid);

    Integer insert(Notebook note);

    Integer update(Notebook note);

    Integer delete(Integer id);
}
