package com.timf.teamfreash.service;

import com.timf.teamfreash.model.DeliveryDriver;
import com.timf.teamfreash.model.ShippingCompany;
import com.timf.teamfreash.model.exception.DeliveryDriverNotFoundException;
import com.timf.teamfreash.repository.DeliveryDriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryDriverService {
    private DeliveryDriverRepo deliveryDriverRepo;
    private ShippingCompanyService shippingCompanyService;

    @Autowired
    public DeliveryDriverService(DeliveryDriverRepo deliveryDriverRepo, ShippingCompanyService shippingCompanyService) {
        this.deliveryDriverRepo = deliveryDriverRepo;
        this.shippingCompanyService = shippingCompanyService;
    }

    @Transactional
    public DeliveryDriver createDeliveryDriver(DeliveryDriver deliveryDriver, String companyName) {
        ShippingCompany shippingCompany = shippingCompanyService.getShippingCompanyFromCompanyName(companyName);
        deliveryDriver.setCompany(shippingCompany);
        shippingCompany.getDrivers().add(deliveryDriver);
        return deliveryDriverRepo.save(deliveryDriver);
    }

    public DeliveryDriver getDeliveryDriverFromId(long id) {
        return deliveryDriverRepo.findById(id).orElseThrow(() ->
                new DeliveryDriverNotFoundException(id));
    }

    @Transactional
    public DeliveryDriver deleteDeliveryDriverFromId(long id) {
        DeliveryDriver deliveryDriver = getDeliveryDriverFromId(id);
        deliveryDriverRepo.delete(deliveryDriver);
        return deliveryDriver;
    }
}
