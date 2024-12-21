package com.cosmocats.api.featuretoggle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FeatureNotAvailableException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Feature '%s' is not enabled.";

    public FeatureNotAvailableException(String featureName) {
        super(String.format(ERROR_MESSAGE, featureName));
    }
}
