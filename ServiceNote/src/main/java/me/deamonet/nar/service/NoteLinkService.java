package me.deamonet.nar.service.implement;

import me.deamonet.nar.entity.Note;
import me.deamonet.nar.entity.NoteLink;
import me.deamonet.nar.mariamapper.MariaNoteMapper;
import me.deamonet.nar.nebulamapper.NebulaNoteMapper;
import me.deamonet.nar.service.NoteLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteLinkServiceImplement implements NoteLinkService {
    @Autowired
    MariaNoteMapper mariaNoteMapper;
    @Autowired
    NebulaNoteMapper nebulaNoteMapper;

    @Override
    public Integer insertLink(Note note1, Note note2, NoteLink link) {

        return null;
    }

    @Override
    public Integer updateLink(Note note1, Note note2, NoteLink link) {
        return null;
    }

    @Override
    public Boolean existsLink(NoteLink link) {
        return null;
    }

    @Override
    public Boolean existsLink(String startId, String endId, NoteLink link) {
        return null;
    }
}
