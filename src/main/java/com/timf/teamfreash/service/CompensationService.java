package com.timf.teamfreash.service;

import com.timf.teamfreash.model.Compensation;
import com.timf.teamfreash.model.VOC;
import com.timf.teamfreash.model.exception.CompensationNotFoundException;
import com.timf.teamfreash.model.type.IssueType;
import com.timf.teamfreash.repository.CompensationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompensationService {
    private CompensationRepo compensationRepo;
    private VOCService vocService;

    @Autowired
    public CompensationService(CompensationRepo compensationRepo, VOCService vocService) {
        this.compensationRepo = compensationRepo;
        this.vocService = vocService;
    }

    @Transactional
    public Compensation createCompensation(Compensation compensation, long vocId) {
        VOC voc = vocService.getVOCFromId(vocId);
        compensation.setVoc(voc);
        return compensationRepo.save(compensation);
    }

    @Transactional
    public Compensation createCompensationFromPenaltyAmount(Compensation compensation, long vocId) {
        VOC voc = vocService.getVOCFromId(vocId);
        compensation.setVoc(voc);
        compensation.setAmount(voc.getPenalty().getAmount());
        return compensationRepo.save(compensation);
    }

    public Compensation getCompensationFromId(long id) {
        return compensationRepo.findById(id).orElseThrow(() ->
                new CompensationNotFoundException(id));
    }

    public List<Compensation> getAllCompensations() {
        return compensationRepo.findAll();
    }

    public List<Compensation> getCompensationsFromDefendantId(long defendantId) {
        return compensationRepo.findByVoc_DefendantId(defendantId);
    }

    @Transactional
    public Compensation deleteCompensationFromId(long id) {
        Compensation compensation = getCompensationFromId(id);
        compensationRepo.delete(compensation);
        return compensation;
    }


}
