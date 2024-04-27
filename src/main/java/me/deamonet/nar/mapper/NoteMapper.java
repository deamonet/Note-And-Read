package me.deamonet.nar.mapper;

import me.deamonet.nar.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoteMapper {
    Integer selectTotalNumber(@Param("notebookid") Integer notebookid);

    Note select(@Param("id") Integer id);

    List<Note> selectMultiple(List<Integer> ids);

    List<Note> selectPage(@Param("notebookid") Integer notebookid, @Param("offset") Integer offset, @Param("page_size") Integer pageSize);

    List<Note> selectAll(@Param("notebookid") Integer notebookid);

    List<Note> selectSearch(@Param("uid") Integer uid, @Param("search_value") String searchValue);

    Integer insert(Note note);

    Integer update(Note note);

    Integer delete(Integer id);
}
