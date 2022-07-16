package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity
public class Compensation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @OneToOne
//    private VOC voc;
    @Column(name = "defendant_id")
    private Long defendant_id;
    @Column(name = "amount")
    private Long amount;
}
