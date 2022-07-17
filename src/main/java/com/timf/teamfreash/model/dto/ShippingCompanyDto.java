package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.ShippingCompany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShippingCompanyDto {
    private long id;
    private String companyRegisterNumber;
    private String companyName;
    private List<DeliveryDriverDto> drivers = new ArrayList<>();

    public static ShippingCompanyDto from(ShippingCompany shippingCompany) {
        ShippingCompanyDto shippingCompanyDto = new ShippingCompanyDto();
        shippingCompanyDto.setId(shippingCompany.getId());
        shippingCompanyDto.setCompanyRegisterNumber(shippingCompany.getCompanyRegisterNumber());
        shippingCompanyDto.setCompanyName(shippingCompany.getCompanyName());
        shippingCompanyDto.setDrivers(shippingCompany.getDrivers().stream().map(DeliveryDriverDto::from).collect(Collectors.toList()));

        return shippingCompanyDto;
    }
}
