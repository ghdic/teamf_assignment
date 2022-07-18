package com.timf.teamfreash.service;

import com.timf.teamfreash.model.Penalty;
import com.timf.teamfreash.model.VOC;
import com.timf.teamfreash.model.exception.PenaltyNotFoundException;
import com.timf.teamfreash.model.exception.VOCNotFoundException;
import com.timf.teamfreash.model.type.IssueType;
import com.timf.teamfreash.repository.PenaltyRepo;
import com.timf.teamfreash.repository.VOCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PenaltyService {
    private PenaltyRepo penaltyRepo;

    @Autowired
    public PenaltyService(PenaltyRepo penaltyRepo) {
        this.penaltyRepo = penaltyRepo;
    }

    @Transactional
    public Penalty createPenalty(Penalty penalty, VOC voc) {
        penalty.setPrice(50000L); // 구현이 따로 안되어있으므로 배송제품비용 5만원 고정
        penalty.setAmount(getPenaltyAmount(penalty.getReason(), penalty.getPrice()));
        penalty.setChecked(false);
        penalty.setVoc(voc);
        return penaltyRepo.save(penalty);
    }

    public Penalty getPenaltyById(long id) {
        return penaltyRepo.findById(id).orElseThrow(() ->
                new PenaltyNotFoundException(id));
    }

    public Penalty setPenaltyChecked(long id, boolean checked) {
        Penalty penalty = getPenaltyById(id);
        penalty.setChecked(checked);
        return penaltyRepo.save(penalty);
    }

    public Long getPenaltyAmount(IssueType issue) {
        return getPenaltyAmount(issue, 0L);
    }



    public Long getPenaltyAmount(IssueType issue, Long price) {
        switch (issue) {
            case 배송지연: // 제품비 20% 배송
                return Double.valueOf(-price * 0.2).longValue();
            case 배송품파손: // 제품비 50% 배상
                return Double.valueOf(-price * 0.5).longValue();
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
