package me.deamonet.nar.service;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.Note;
import me.deamonet.nar.entity.Relation;
import me.deamonet.nar.mapper.NoteMapper;
import me.deamonet.nar.mapper.RelationMapper;
import me.deamonet.nar.transmit.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class NoteService {
    final
    NoteMapper noteMapper;
    final
    RelationMapper relationMapper;

    public NoteService(NoteMapper noteMapper, RelationMapper relationMapper) {
        this.noteMapper = noteMapper;
        this.relationMapper = relationMapper;
    }

    public Integer getTotalNumber(int notebookid) {
        return noteMapper.selectTotalNumber(notebookid);
    }

    public NoteWithLinks get(Integer id){
        Note note = noteMapper.select(id);
        List<Relation> relationList = relationMapper.selectByNote(note.getId());
        return new NoteWithLinks(note, relationList);
    }

    public NoteSubmitted getSubbmited(Integer id) {
        Note note = noteMapper.select(id);
        List<Relation> relationList = relationMapper.selectByNote(note.getId());
        NoteSubmitted noteSubmitted =  new NoteSubmitted(note.getId(), note.getUid(), note.getNotebookid(), note.getTitle(), note.getContent());
        return propertyRestorer(noteSubmitted, relationList);
    }

    public List<Note> searchList(Integer uid, String searchValue) {
        return noteMapper.selectSearch(uid, searchValue);
    }

    public List<Note> getPage(Integer notebookid, Integer offset, Integer pageSize) {
        return noteMapper.selectPage(notebookid, offset, pageSize);
    }

    public List<Note> getAll(Integer notebookid) {
        return noteMapper.selectAll(notebookid);
    }

    public NoteNet noteNet(int notebookid) {
        List<Relation> relationList = relationMapper.selectByNotebook(notebookid);
        Set<NoteNode> nodes = new HashSet<>();
        List<NoteLink> links = new LinkedList<>();
        for (Relation relation : relationList) {
            nodes.add(new NoteNode(relation.getSource(), relation.getSourceTitle()));
            nodes.add(new NoteNode(relation.getTarget(), relation.getTargetTitle()));
            links.add(new NoteLink(relation.getSourceTitle(), relation.getTargetTitle()));
        }
        return new NoteNet(nodes, links);
    }

    @Transactional
    public Integer addNote(NoteSubmitted note) {
        Note newNote = new Note(note.getUid(), note.getNotebookid(), note.getTitle(), note.getContent());
        noteMapper.insert(newNote);
        Integer id = newNote.getId();
        List<Integer> targetNodes = propertyShaper(note);
        for (Integer i : targetNodes) {
            relationMapper.insert(new Relation(note.getNotebookid(), id, i, note.getTitle(), noteMapper.select(i).getTitle()));
            relationMapper.insert(new Relation(note.getNotebookid(), i, id, noteMapper.select(i).getTitle(), note.getTitle()));
        }
        return id;
    }

    @Transactional
    public Integer editNote(NoteSubmitted note) {
        Note newNote = new Note(note.getId(), note.getTitle(), note.getContent());
        int result = noteMapper.update(newNote);
        List<Integer> targetNodes = propertyShaper(note);
        for (Integer i : targetNodes) {
            result += relationMapper.insert(new Relation(note.getNotebookid(), note.getId(), i, note.getTitle(), noteMapper.select(i).getTitle()));
        }
        return result;
    }

    @Transactional
    public Integer deleteNote(Integer id) {
        return noteMapper.delete(id) + relationMapper.deleteByNote(id, id);
    }

    @Transactional
    public Integer deleteRelation(Integer id, Integer id2){
        return relationMapper.delete(id, id2) + relationMapper.delete(id2, id);
    }


    public List<Integer> propertyShaper(NoteSubmitted note) {
        List<Integer> linkedNoteList = new LinkedList<>();
        if (!note.getLinkednote1().equals("")) {
            linkedNoteList.add(Integer.parseInt(note.getLinkednote1()));
        }
        if (!note.getLinkednote2().equals("")) {
            linkedNoteList.add(Integer.parseInt(note.getLinkednote2()));
        }
        if (!note.getLinkednote3().equals("")) {
            linkedNoteList.add(Integer.parseInt(note.getLinkednote3()));
        }
        if (!note.getLinkednote4().equals("")) {
            linkedNoteList.add(Integer.parseInt(note.getLinkednote4()));
        }
        if (!note.getLinkednote5().equals("")) {
            linkedNoteList.add(Integer.parseInt(note.getLinkednote5()));
        }
        return linkedNoteList;
    }

    public NoteSubmitted propertyRestorer(NoteSubmitted note, List<Relation> list){
        if (list.size() > 0){note.setLinkednote1(list.get(0).getTarget().toString());}
        if (list.size() > 1){note.setLinkednote1(list.get(1).getTarget().toString());}
        if (list.size() > 2){note.setLinkednote1(list.get(2).getTarget().toString());}
        if (list.size() > 3){note.setLinkednote1(list.get(3).getTarget().toString());}
        if (list.size() > 4){note.setLinkednote1(list.get(4).getTarget().toString());}
        if (list.size() > 5){note.setLinkednote1(list.get(5).getTarget().toString());}
        return note;
    }
}
