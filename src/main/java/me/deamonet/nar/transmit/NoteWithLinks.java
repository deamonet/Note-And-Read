package me.deamonet.nar.transmit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.deamonet.nar.entity.Note;
import me.deamonet.nar.entity.Relation;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class NoteWithLinks {
    Note note;
    List<Relation> relationList;
}
