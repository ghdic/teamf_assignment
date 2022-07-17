package com.timf.teamfreash.service;

import com.timf.teamfreash.model.VOC;
import com.timf.teamfreash.model.exception.VOCNotFoundException;
import com.timf.teamfreash.repository.VOCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VOCService {
    private VOCRepo vocRepo;

    @Autowired
    public VOCService(VOCRepo vocRepo) {
        this.vocRepo = vocRepo;
    }

    public VOC createVOC(VOC voc) {
        return vocRepo.save(voc);
    }

    public List<VOC> getAllVOC() {
        return vocRepo.findAll();
    }

    public VOC getVOCFromId(long id) {
        return vocRepo.findById(id).orElseThrow(() ->
                new VOCNotFoundException(id));
    }

    public VOC editVOCFromId(long id, VOC voc) {
        VOC to_edit = getVOCFromId(id);
        // editable property
        vocRepo.save(to_edit);
        return to_edit;
    }

    @Transactional
    public VOC deleteVOCFromId(long id) {
        VOC voc = getVOCFromId(id);
        vocRepo.delete(voc);
        return voc;
    }
}
