package com.timf.teamfreash.model;

import javax.persistence.*;

@Entity(name = "DeliverDriver")
@AttributeOverride(name="id", column = @Column(name="delivery_driver_id"))
public class DeliveryDriver extends Clinet {
    @Column(name = "name")
    private String name;

//    @ManyToOne
//    ShippingCompany company;
}
