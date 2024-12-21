package com.cosmocats.api.service;

import com.cosmocats.api.featuretoggle.FeatureToggle;
import com.cosmocats.api.featuretoggle.FeatureToggleEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KittyProductService {

    @FeatureToggle(FeatureToggleEnum.KITTY_PRODUCTS)
    public List<String> getKittyProducts() {
        return List.of("Catnip Toys", "Laser Pointers", "Space-themed Scratching Posts");
    }
}