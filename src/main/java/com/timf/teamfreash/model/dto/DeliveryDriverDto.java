package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.DeliveryDriver;
import com.timf.teamfreash.model.type.BankType;
import lombok.Data;

@Data
public class DeliveryDriverDto {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String account;
    private BankType bankType;

    public static DeliveryDriverDto from(DeliveryDriver deliveryDriver) {
        DeliveryDriverDto deliveryDriverDto = new DeliveryDriverDto();
        deliveryDriverDto.setId(deliveryDriver.getId());
        deliveryDriverDto.setUserId(deliveryDriver.getUserId());
        deliveryDriverDto.setPassword(deliveryDriver.getPassword());
        deliveryDriverDto.setName(deliveryDriver.getName());
        deliveryDriverDto.setPhoneNumber(deliveryDriver.getPhoneNumber());
        deliveryDriverDto.setAccount(deliveryDriver.getAccount());
        deliveryDriverDto.setBankType(deliveryDriver.getBankType());

        return deliveryDriverDto;
    }
}
