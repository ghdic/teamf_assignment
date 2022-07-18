package com.timf.teamfreash.service;

import com.timf.teamfreash.model.Penalty;
import com.timf.teamfreash.model.VOC;
import com.timf.teamfreash.model.exception.VOCNotFoundException;
import com.timf.teamfreash.model.type.IssueType;
import com.timf.teamfreash.repository.VOCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VOCService {
    private VOCRepo vocRepo;
    private PenaltyService penaltyService;

    @Autowired
    public VOCService(VOCRepo vocRepo, PenaltyService penaltyService) {
        this.vocRepo = vocRepo;
        this.penaltyService = penaltyService;
    }

    public VOC createVOC(VOC voc, IssueType reason) {
        VOC saved_voc = vocRepo.save(voc);
        Penalty penalty = penaltyService.createPenalty(new Penalty(reason), voc);
        voc.setPenalty(penalty);
        return saved_voc;
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
