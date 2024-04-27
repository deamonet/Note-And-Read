package me.deamonet.nar.transmit;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteSubmitted {
    Integer id;
    Integer uid;
    Integer notebookid;
    String title;
    String content;
    String linkednote1;
    String linkednote2;
    String linkednote3;
    String linkednote4;
    String linkednote5;

    public NoteSubmitted(Integer id, Integer uid, Integer notebookid, String title, String content) {
        this.id = id;
        this.uid = uid;
        this.notebookid = notebookid;
        this.title = title;
        this.content = content;
    }
}
