package me.deamonet.nar.controller;

import me.deamonet.nar.entity.Note;
import me.deamonet.nar.service.NoteService;
import me.deamonet.nar.transmit.NoteNet;
import me.deamonet.nar.transmit.NoteSubmitted;
import me.deamonet.nar.transmit.NoteWithLinks;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{uid:\\d+}/note")
public class NoteController {
    final
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/total")
    public Integer getTotalNumber(@PathVariable("uid") int uid,
                                  @RequestParam("notebookid") int notebookid) {
        return noteService.getTotalNumber(notebookid);
    }

    @GetMapping("/list")
    public List<Note> getPage(@PathVariable("uid") int uid,
                                  @RequestParam("notebookid") int notebookid,
                                  @RequestParam("offset") int offset,
                                  @RequestParam("page_size") int pageSize) {
        return noteService.getPage(notebookid, offset, pageSize);
    }

    @GetMapping("/all")
    public List<Note> getAll(@PathVariable("uid") int uid,@RequestParam("notebookid") int notebookid){
        return noteService.getAll(notebookid);
    }

    @GetMapping("/{id:\\d+}")
    public NoteWithLinks getNote(@PathVariable("uid") int uid,
                                 @PathVariable("id") int id) {
        return noteService.get(id);
    }

    @GetMapping("/submit/{id:\\d+}")
    public NoteSubmitted getNoteSubmitted(@PathVariable("uid") int uid,
                                          @PathVariable("id") int id) {
        return noteService.getSubbmited(id);
    }


    @PostMapping()
    public Integer addNote(@RequestBody NoteSubmitted note) {
        return noteService.addNote(note);
    }

    @PutMapping("/{id:\\d+}")
    public Integer editNote(@PathVariable("id") int id, @RequestBody NoteSubmitted note) {
        note.setId(id);
        return noteService.editNote(note);
    }

    @DeleteMapping("/{id:\\d+}")
    public Integer deleteNote(@PathVariable int uid, @PathVariable("id") int id) {
        return noteService.deleteNote(id);
    }

    @GetMapping("/net")
    public NoteNet noteNet(@PathVariable int uid, @RequestParam("notebookid") int notebookid){
        return noteService.noteNet(notebookid);
    }
}