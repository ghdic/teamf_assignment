package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity(name = "Provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "manager_name")
    private String manager_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;
}
