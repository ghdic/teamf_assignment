package com.timf.teamfreash.service;

import com.timf.teamfreash.model.ShippingCompany;
import com.timf.teamfreash.model.exception.ShippingCompanyNotFoundException;
import com.timf.teamfreash.repository.ShippingCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShippingCompanyService {
    private ShippingCompanyRepo shippingCompanyRepo;

    @Autowired
    public ShippingCompanyService(ShippingCompanyRepo shippingCompanyRepo) {
        this.shippingCompanyRepo = shippingCompanyRepo;
    }

    public ShippingCompany createShippingCompany(ShippingCompany shippingCompany) {
        return shippingCompanyRepo.save(shippingCompany);
    }

    public ShippingCompany getShippingCompanyFromId(long id) {
        return shippingCompanyRepo.findById(id).orElseThrow(() ->
                new ShippingCompanyNotFoundException(id));
    }

    public ShippingCompany getShippingCompanyFromCompanyName(String companyName) {
        return shippingCompanyRepo.findByCompanyName(companyName).orElseThrow(() ->
                new ShippingCompanyNotFoundException(companyName));
    }

    @Transactional
    public ShippingCompany deleteShippingCompanyFromId(long id) {
        ShippingCompany shippingCompany = getShippingCompanyFromId(id);
        shippingCompanyRepo.delete(shippingCompany);
        return shippingCompany;
    }
}
