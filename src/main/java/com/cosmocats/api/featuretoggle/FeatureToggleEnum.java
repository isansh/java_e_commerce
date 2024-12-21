package com.cosmocats.api.featuretoggle;

import lombok.Getter;

@Getter
public enum FeatureToggleEnum {

    COSMO_CATS("cosmoCats"),
    KITTY_PRODUCTS("kittyProducts");

    private final String toggleName;

    FeatureToggleEnum(String toggleName) {
        this.toggleName = toggleName;
    }
}