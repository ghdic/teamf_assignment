package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.ComplaintDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Complaint")
@Getter @Setter
public class Complaint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id")
    private VOC voc;
    @Column(name = "reason")
    private String reason;
    @Column(name = "adjudicate")
    private boolean adjudicate;

    public static Complaint from(ComplaintDto complaintDto) {
        Complaint complaint = new Complaint();
        complaint.setReason(complaintDto.getReason());
        complaint.setAdjudicate(complaintDto.isAdjudicate());
        return complaint;
    }
}
