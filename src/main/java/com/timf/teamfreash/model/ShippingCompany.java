package com.timf.teamfreash.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "ShippingCompany")
@AttributeOverride(name="id", column = @Column(name="shipping_company_id"))
public class ShippingCompany extends Clinet {
    @Column(name = "company_name")
    private String company_name;
//    @OneToMany
//    List<DeliveryDriver> drivers;
}
