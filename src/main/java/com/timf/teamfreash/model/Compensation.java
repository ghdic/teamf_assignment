package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity
public class Compensation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id")
    private VOC voc;
    @Column(name = "amount")
    private Long amount;
}
