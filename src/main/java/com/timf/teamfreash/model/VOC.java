package com.timf.teamfreash.model;


import com.timf.teamfreash.model.dto.VOCDto;
import com.timf.teamfreash.model.type.ClientType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "VOC")
@Getter @Setter
public class VOC {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "complainer_id")
    private long complainerId;
    @Enumerated(value = EnumType.STRING)
    private ClientType complainerType;
    @Column(name = "defendant_id")
    private long defendantId;
    @Enumerated(value = EnumType.STRING)
    private ClientType defendantType;
    @Column(name = "reason")
    private String reason;
    @OneToMany(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Compensation> compensationList;
    @OneToOne(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Penalty penalty;

    public VOC() {
    }

    public static VOC from(VOCDto vocDto) {
        VOC voc = new VOC();
        voc.setComplainerId(vocDto.getComplainerId());
        voc.setComplainerType(vocDto.getComplainerType());
        voc.setDefendantId(vocDto.getDefendantId());
        voc.setDefendantType(vocDto.getDefendantType());
        voc.setReason(vocDto.getReason());

        return voc;
    }

    public VOC(long complainerId, ClientType complainerType, long defendantId, ClientType defendantType, String reason) {
        this.complainerId = complainerId;
        this.complainerType = complainerType;
        this.defendantId = defendantId;
        this.defendantType = defendantType;
        this.reason = reason;
    }
}
