package me.deamonet.nar.mariamapper;

import me.deamonet.nar.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MariaNoteMapper {
    Note selectNoteById(Integer id);
    List<Note> selectNoteByIds(List<Integer> ids);
    List<Note> selectNoteByPage(Integer offset, Integer pageSize, Integer uid);
    List<Note> selectNoteByPage(Integer offset, Integer pageSize, Integer uid, Integer noteBookId);
    List<Note> selectNoteBySearch(Integer offset, Integer pageSize, String searchValue);
    List<Note> selectNoteBySearch(Integer offset, Integer pageSize, String searchValue, Integer uid);
    List<Note> selectNoteBySearch(Integer offset, Integer pageSize, String searchValue, Integer uid, Integer noteBookId);
    Integer insertNote(Note note);
    Integer updateNote(Note note);
    Integer deleteNoteById(Integer id);
}
