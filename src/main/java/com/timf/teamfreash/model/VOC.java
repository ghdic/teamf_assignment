package com.timf.teamfreash.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "VOC")
public class VOC {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private long complainer_id;
    private String complainer_type;
    private long defendant_id;
    private String defendant_type;
    @Column(name = "reason")
    private String reason;
    @OneToMany(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Compensation> penalty = new ArrayList<>();
    private boolean is_complete;

    @OneToMany(
            mappedBy = "voc",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Complaint> protest = new ArrayList<>();
}
