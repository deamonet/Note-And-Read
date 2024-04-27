package me.deamonet.nar.service;

import me.deamonet.nar.entity.Notebook;
import me.deamonet.nar.mapper.NotebookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * crud notebooks
 */
@Service
public class NotebookService {
    final
    NotebookMapper notebookMapper;

    public NotebookService(NotebookMapper notebookMapper) {
        this.notebookMapper = notebookMapper;
    }

    public Integer getTotalNumber(int uid){
        return notebookMapper.selectTotalNumber(uid);
    }

    public Notebook get(int id){
        return notebookMapper.select(id);
    }

    public List<Notebook> getNotebooks(int uid){
        return notebookMapper.selectAll(uid);
    }
    @Transactional
    public Integer addNotebook(Notebook Notebook) {
        return notebookMapper.insert(Notebook);
    }
    @Transactional
    public Integer deleteNotebook(Integer id) {
        return notebookMapper.delete(id);
    }
    @Transactional
    public Integer editNotebook(Notebook Notebook) {
        return notebookMapper.update(Notebook);
    }
}
