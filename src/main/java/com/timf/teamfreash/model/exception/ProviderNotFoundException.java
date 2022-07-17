package com.timf.teamfreash.model.exception;

import java.text.MessageFormat;

public class ProviderNotFoundException extends RuntimeException {

    public ProviderNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Provider with id: {0}", id));
    }

    public ProviderNotFoundException(String userId) {
        super(MessageFormat.format("Could not find Provider with userId: {0}", userId));
    }
}
