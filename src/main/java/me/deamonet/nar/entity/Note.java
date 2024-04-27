package me.deamonet.nar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Integer id;
    private Integer uid;
    private Integer notebookid;
    private String title;
    private String content;
    private Date firstEdit;
    private Date lastEdit;


    public Note(Integer uid, Integer notebookid, String title, String content) {
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.notebookid = notebookid;
    }

    public Note(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

