package com.cosmocats.api.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CosmicWordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWordCheck {
    String message() default "Field must contain cosmic terms like 'star', 'galaxy', 'comet'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

