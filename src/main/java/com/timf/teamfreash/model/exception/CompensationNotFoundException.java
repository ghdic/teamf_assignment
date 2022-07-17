package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class CompensationNotFoundException extends RuntimeException {

    public CompensationNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Compensation with id: {0}", id));
    }
}
