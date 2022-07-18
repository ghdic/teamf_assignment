package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.CompensationDto;
import com.timf.teamfreash.model.type.IssueType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Compensation")
@Getter @Setter
public class Compensation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id")
    private VOC voc;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "complete")
    private boolean complete;

    public static Compensation from(CompensationDto compensationDto) {
        Compensation compensation = new Compensation();
        compensation.setAmount(compensationDto.getAmount());

        return compensation;
    }

    public Compensation() {

    }
}
