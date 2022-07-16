package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity(name = "Complaint")
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
}
