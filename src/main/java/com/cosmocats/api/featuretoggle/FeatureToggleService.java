package com.cosmocats.api.featuretoggle;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class FeatureToggleService {

    private final ConcurrentHashMap<String, Boolean> featureToggles;

    public FeatureToggleService(FeatureToggleProperties featureToggleProperties) {
        this.featureToggles = new ConcurrentHashMap<>(featureToggleProperties.getToggles());
    }

    public boolean isFeatureEnabled(String featureName) {
        return featureToggles.getOrDefault(featureName, false);
    }

    public void enableFeature(String featureName) {
        featureToggles.put(featureName, true);
    }

    public void disableFeature(String featureName) {
        featureToggles.put(featureName, false);
    }
}
