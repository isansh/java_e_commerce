package com.cosmocats.api.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.contains("star") || value.contains("galaxy") || value.contains("comet"));
    }
}


