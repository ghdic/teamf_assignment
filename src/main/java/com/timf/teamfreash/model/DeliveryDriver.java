package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity(name = "DeliverDriver")
public class DeliveryDriver{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_company_id")
    ShippingCompany company;

}
