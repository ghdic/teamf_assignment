package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.PenaltyDto;
import com.timf.teamfreash.model.type.IssueType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity(name = "Penalty")
public class Penalty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "reason")
    private IssueType reason;
    @Column(name = "price")
    long price;
    @Column(name = "amount")
    long amount;
    @Column(name = "checked")
    private boolean checked;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "voc_id")
    private VOC voc;

    @OneToMany(
            mappedBy = "penalty",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Complaint> complaintList = new ArrayList<>();

    public static Penalty from(PenaltyDto penaltyDto) {
        Penalty penalty = new Penalty();
        penalty.setReason(penaltyDto.getReason());
        penalty.setChecked(penaltyDto.isChecked());
        return penalty;
    }

    public Penalty() {
    }

    public Penalty(IssueType reason) {
        this.reason = reason;
    }
}
