package com.timf.teamfreash.model;

import com.timf.teamfreash.model.dto.DeliveryDriverDto;
import com.timf.teamfreash.model.type.BankType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "DeliveryDriver")
@Getter @Setter
public class DeliveryDriver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_id", unique = true)
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "account")
    private String account;
    @Column(name = "bank_type")
    @Enumerated(value = EnumType.STRING)
    private BankType bankType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_company_id")
    ShippingCompany company;

    public DeliveryDriver() {
    }

    public DeliveryDriver(String userId, String password, String name, String phoneNumber, String account, BankType bankType) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.bankType = bankType;
    }

    public static DeliveryDriver from(DeliveryDriverDto deliveryDriverDto) {
        DeliveryDriver deliveryDriver = new DeliveryDriver();
        deliveryDriver.setUserId(deliveryDriverDto.getUserId());
        deliveryDriver.setPassword(deliveryDriverDto.getPassword());
        deliveryDriver.setName(deliveryDriverDto.getName());
        deliveryDriver.setPhoneNumber(deliveryDriverDto.getPhoneNumber());
        deliveryDriver.setAccount(deliveryDriverDto.getAccount());
        deliveryDriver.setBankType(deliveryDriverDto.getBankType());

        return deliveryDriver;
    }
}
