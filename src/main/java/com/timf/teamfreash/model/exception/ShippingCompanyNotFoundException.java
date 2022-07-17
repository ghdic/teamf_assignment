package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class ShippingCompanyNotFoundException extends RuntimeException {

    public ShippingCompanyNotFoundException(Long id) {
        super(MessageFormat.format("Could not find ShippingCompany with id: {0}", id));
    }

    public ShippingCompanyNotFoundException(String companyName) {
        super(MessageFormat.format("Could not find ShippingCompany with companyName: {0}", companyName));
    }
}
