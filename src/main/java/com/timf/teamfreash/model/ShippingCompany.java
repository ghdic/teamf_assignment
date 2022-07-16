package com.timf.teamfreash.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ShippingCompany")
public class ShippingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "company_name")
    private String company_name;
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    List<DeliveryDriver> drivers = new ArrayList<>();
}
