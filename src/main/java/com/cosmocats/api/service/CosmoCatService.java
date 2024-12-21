package com.cosmocats.api.service;

import com.cosmocats.api.featuretoggle.FeatureToggle;
import com.cosmocats.api.featuretoggle.FeatureToggleEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmoCatService {

    @FeatureToggle(FeatureToggleEnum.COSMO_CATS)
    public List<String> getCosmoCats() {
        return List.of("Lunar Whiskers", "Stellar Paws", "Galaxy Tail");
    }
}