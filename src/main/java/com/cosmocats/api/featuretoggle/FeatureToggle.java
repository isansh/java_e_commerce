package com.cosmocats.api.featuretoggle;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FeatureToggle {
    FeatureToggleEnum value();
}
