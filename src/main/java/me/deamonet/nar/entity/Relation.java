package me.deamonet.nar.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    private Integer id;
    private Integer notebookid;
    private Integer source;
    private Integer target;
    private String sourceTitle;
    private String targetTitle;

    public Relation(Integer notebookid, Integer source, Integer target, String sourceTitle, String targetTitle) {
        this.notebookid = notebookid;
        this.source = source;
        this.target = target;
        this.sourceTitle = sourceTitle;
        this.targetTitle = targetTitle;
    }
}
