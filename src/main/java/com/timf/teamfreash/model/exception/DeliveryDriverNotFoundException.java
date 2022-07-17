package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class DeliveryDriverNotFoundException extends RuntimeException {

    public DeliveryDriverNotFoundException(Long id) {
        super(MessageFormat.format("Could not find DeliveryDriver with id: {0}", id));
    }
}
