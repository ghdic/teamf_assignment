package com.timf.teamfreash.model;

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


    private boolean check;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    private VOC voc;

    @OneToMany(
            mappedBy = "penalty",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Complaint> complaintList = new ArrayList<>();

    @OneToMany(
            mappedBy = "penalty",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Compensation> compensationList = new ArrayList<>();
}
