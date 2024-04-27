package me.deamonet.nar.transmit;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class NoteNet {
    public Set<NoteNode> nodes;
    public List<NoteLink> links;
}
