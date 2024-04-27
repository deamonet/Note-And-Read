package me.deamonet.nar.service.implement;

import me.deamonet.nar.entity.Note;
import me.deamonet.nar.mariamapper.MariaNoteMapper;
import me.deamonet.nar.nebulamapper.NebulaNoteMapper;
import me.deamonet.nar.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteServiceImplement implements NoteService {
    @Autowired
    MariaNoteMapper mariaNoteMapper;
    @Autowired
    NebulaNoteMapper nebulaNoteMapper;

    @Override
    @Transactional
    public Note getNoteById(Integer nid) {
        return mariaNoteMapper.selectNoteById(nid);
    }


    @Override
    @Transactional
    public List<Note> getNoteByPage(Integer offset, Integer pageSize){
        return mariaNoteMapper.selectNoteByPage(offset, pageSize);
    }

    @Override
    @Transactional
    public List<Note> getNoteBySearch(String searchValue, Integer offset, Integer pageSize){
        return mariaNoteMapper.selectNoteBySearch(searchValue, offset, pageSize);
    }

    @Override
    @Transactional
    public Integer addNote(Note note) {
        return mariaNoteMapper.insertNote(note) + nebulaNoteMapper.insert(note);
    }

    @Override
    @Transactional
    public Integer deleteNote(Integer nid) {
        return mariaNoteMapper.deleteNoteById(nid) + nebulaNoteMapper.deleteById("u" + nid);
    }

    @Override
    @Transactional
    public Integer editNote(Note note) {
        nebulaNoteMapper.updateById(note);
        return mariaNoteMapper.updateNote(note);
    }

    @Override
    public List<Note> getLinkedNote(Integer uid) {
        List<Note> noteList = nebulaNoteMapper.selectNoteByUser(uid);
        List<Note> mariaNoteList = mariaNoteMapper.selectNoteByIds(
                noteList.stream()
                        .map(Note -> Integer.parseInt(Note.getNid().substring(1)))
                        .collect(Collectors.toList()));
        return Objects.isNull(mariaNoteList) ? Collections.emptyList() : mariaNoteList;
    }
}
