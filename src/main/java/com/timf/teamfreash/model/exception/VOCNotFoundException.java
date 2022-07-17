package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class VOCNotFoundException extends RuntimeException {

    public  VOCNotFoundException(Long id) {
        super(MessageFormat.format("Could not find VOC with id: {0}", id));
    }
}
