package com.cosmocats.api.featuretoggle;

import com.cosmocats.api.featuretoggle.FeatureToggle;
import com.cosmocats.api.featuretoggle.FeatureToggleService;
import com.cosmocats.api.featuretoggle.FeatureToggleEnum;
import com.cosmocats.api.featuretoggle.exception.FeatureNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Before("@annotation(featureToggle)")
    public void checkFeatureToggle(FeatureToggle featureToggle) {
        FeatureToggleEnum toggleEnum = featureToggle.value();
        String featureName = toggleEnum.getToggleName();

        if (!featureToggleService.isFeatureEnabled(featureName)) {
            log.warn("Feature {} is not enabled!", featureName);
            throw new FeatureNotAvailableException(featureName);
        }
    }
}