package me.deamonet.nar.controller;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.Notebook;
import me.deamonet.nar.service.NotebookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/{uid:\\d+}/notebook")
public class NotebookController {
    final
    NotebookService service;
    final
    Integer pageSize;

    public NotebookController(NotebookService service) {
        this.service = service;
        this.pageSize = 10;
    }
    
    @GetMapping("/{notebookid:\\d+}")
    public Notebook get(@PathVariable("notebookid") int notebookid){
        return service.get(notebookid);
    }

    @GetMapping("/total")
    public Integer total(@PathVariable("uid") int uid) {
        return service.getTotalNumber(uid);
    }

    @GetMapping("/all")
    public List<Notebook> getAll(@PathVariable("uid") int uid) {
        return service.getNotebooks(uid);
    }

    @PostMapping
    public Integer addNotebook(@PathVariable("uid") int uid,
                               @RequestBody Notebook notebook) {
        notebook.setUid(uid);
        return service.addNotebook(notebook);
    }

    @PutMapping("/{notebookid:\\d+}")
    public Integer editNotebook(@PathVariable("uid") int uid,
                                @PathVariable("notebookid") int notebookid,
                                @RequestBody Notebook notebook) {
        notebook.setId(notebookid);
        notebook.setUid(uid);
        return service.editNotebook(notebook);
    }

    @DeleteMapping("/{notebookid:\\d+}")
    public Integer deleteNotebook(@PathVariable("uid") int uid,
                                  @PathVariable("notebookid") int notebookid) {
        Notebook notebook = service.get(notebookid);
        assert notebook.getUid() == uid;
        return service.deleteNotebook(notebookid);
    }
}
