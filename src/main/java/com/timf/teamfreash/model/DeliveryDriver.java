package com.timf.teamfreash.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name="id", column = @Column(name="delivery_driver_no"))
public class DeliveryDriver extends Clinet {

}
