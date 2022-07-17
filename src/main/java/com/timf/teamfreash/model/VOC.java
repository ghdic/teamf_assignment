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
    private long complainer_id;
    @Enumerated(value = EnumType.STRING)
    private ClientType complainer_type;
    @Column(name = "defendant_id")
    private long defendant_id;
    @Enumerated(value = EnumType.STRING)
    private ClientType defendant_type;
    @Column(name = "reason")
    private String reason;
    @OneToMany(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Compensation> penalty;
    private boolean is_completed;

    @OneToMany(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Complaint> protest = new ArrayList<>();

    public VOC() {
    }

    public static VOC from(VOCDto vocDto) {
        VOC voc = new VOC();
        voc.setComplainer_id(vocDto.getComplainer_id());
        voc.setComplainer_type(vocDto.getComplainer_type());
        voc.setDefendant_id(vocDto.getDefendant_id());
        voc.setDefendant_type(vocDto.getDefendant_type());
        voc.setReason(vocDto.getReason());
        voc.set_completed(vocDto.is_complete());

        return voc;
    }

    public VOC(long complainer_id, ClientType complainer_type, long defendant_id, ClientType defendant_type, String reason, boolean is_completed) {
        this.complainer_id = complainer_id;
        this.complainer_type = complainer_type;
        this.defendant_id = defendant_id;
        this.defendant_type = defendant_type;
        this.reason = reason;
        this.is_completed = is_completed;
    }
}
