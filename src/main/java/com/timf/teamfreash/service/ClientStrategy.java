package com.timf.teamfreash.service;

import com.timf.teamfreash.model.dto.DeliveryDriverDto;
import com.timf.teamfreash.model.dto.ProviderDto;
import com.timf.teamfreash.model.dto.ShippingCompanyDto;
import com.timf.teamfreash.model.type.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientStrategy {

    private DeliveryDriverService deliveryDriverService;
    private ProviderService providerService;
    private ShippingCompanyService shippingCompanyService;

    @Autowired
    public ClientStrategy(DeliveryDriverService deliveryDriverService, ProviderService providerService, ShippingCompanyService shippingCompanyService) {
        this.deliveryDriverService = deliveryDriverService;
        this.providerService = providerService;
        this.shippingCompanyService = shippingCompanyService;
    }

    public Object getClient(long id, ClientType type) {
        switch (type) {
            case DeliveryDriver:
                return DeliveryDriverDto.from(deliveryDriverService.getDeliveryDriverFromId(id));
            case Provider:
                return ProviderDto.from(providerService.getProviderFromId(id));
            case ShippingCompany:
                return ShippingCompanyDto.from(shippingCompanyService.getShippingCompanyFromId(id));
            default:
                return null;
        }
    }
}
