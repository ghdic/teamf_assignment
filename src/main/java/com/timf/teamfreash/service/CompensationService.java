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
        voc.getPenalty().add(compensation);

        // 원래는 VOC와 연결된 배송정보에 대한 테이블에서의 제품 결제 정보를 가져와야함
        // 하지만 배송정보까지 구현하면 코드양이 너무 많아지기에 price는 50,000이라고 가정함
        compensation.setAmount(getCompensationAmount(compensation.getReason(), 50000L));
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

    public Long getCompensationAmount(IssueType issue) {
        return getCompensationAmount(issue, 0L);
    }

    public Long getCompensationAmount(IssueType issue, Long price) {
        switch (issue) {
            case 늦은배송: // 제품비 20% 배송
                return -price / 5L;
            case 배송품파손: // 제품비 50% 배상
                return -price / 10L;
            case 배송실수: // 재배송
                return -3000L;
            case 배송물누락: // 재배송(왕복)
                return -5000L;
            case 기타: // 지정가만큼 배상
                return -price;

            default:
                return 0L;
        }
    }
}
