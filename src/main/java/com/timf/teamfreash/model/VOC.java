package com.timf.teamfreash.model;


import javax.persistence.*;

@Entity(name = "VOC")
public class VOC {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
//    @OneToMany
//    Clinet complainer;
//    @OneToOne
//    Clinet defendant;
    @Column(name = "reason")
    private String reason;
//    @OneToOne
//    private Compensation penalty;
    private boolean is_complete;

//    @OneToMany
//    private Complaint protest;
}
