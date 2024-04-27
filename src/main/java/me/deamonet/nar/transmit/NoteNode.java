package me.deamonet.nar.transmit;


import java.util.Objects;

public class NoteNode {
    public int id;
    public String name;

    public NoteNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteNode noteNode = (NoteNode) o;
        return id == noteNode.id && name.equals(noteNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
