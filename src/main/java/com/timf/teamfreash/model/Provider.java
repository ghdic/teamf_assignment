package com.timf.teamfreash.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Provider")
@AttributeOverride(name="id", column = @Column(name="provider_id"))
public class Provider extends Clinet{
    @Column(name = "manager_name")
    private String manager_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;
}
