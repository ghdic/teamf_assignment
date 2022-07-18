package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class PenaltyNotFoundException extends RuntimeException {

    public PenaltyNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Penalty with id: {0}", id));
    }
}
