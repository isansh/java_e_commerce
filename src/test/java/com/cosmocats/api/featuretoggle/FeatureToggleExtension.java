package com.cosmocats.api.featuretoggle;

import com.cosmocats.api.featuretoggle.FeatureToggleEnum;
import com.cosmocats.api.featuretoggle.FeatureToggleService;
import com.cosmocats.api.featuretoggle.exception.FeatureNotAvailableException;
import com.cosmocats.api.service.CosmoCatService;
import com.cosmocats.api.service.KittyProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureToggleExtension {

    @Mock
    private FeatureToggleService featureToggleService;

    private CosmoCatService cosmoCatService;
    private KittyProductService kittyProductService;

    @BeforeEach
    public void setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create services with mocked FeatureToggleService
        cosmoCatService = new CosmoCatService();
        kittyProductService = new KittyProductService();

        // Mock behavior for FeatureToggleService
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.COSMO_CATS.getToggleName())).thenReturn(true);
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.KITTY_PRODUCTS.getToggleName())).thenReturn(true);
    }

    @Test
    public void testGetCosmoCats_featureEnabled() {
        // Arrange: ensure the feature toggle is enabled
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.COSMO_CATS.getToggleName())).thenReturn(true);

        // Act
        List<String> cosmoCats = cosmoCatService.getCosmoCats();

        // Assert
        assertNotNull(cosmoCats);
        assertEquals(3, cosmoCats.size());
        assertTrue(cosmoCats.contains("Lunar Whiskers"));
        assertTrue(cosmoCats.contains("Stellar Paws"));
        assertTrue(cosmoCats.contains("Galaxy Tail"));
    }

    @Test
    public void testGetCosmoCats_featureDisabled() {
        // Arrange: ensure the feature toggle is disabled
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.COSMO_CATS.getToggleName())).thenReturn(false);

        // Act & Assert
        FeatureNotAvailableException thrown = assertThrows(FeatureNotAvailableException.class, () -> {
            cosmoCatService.getCosmoCats();
        });

        assertEquals("Feature cosmoCats is not enabled!", thrown.getMessage());
    }

    @Test
    public void testGetKittyProducts_featureEnabled() {
        // Arrange: ensure the feature toggle is enabled
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.KITTY_PRODUCTS.getToggleName())).thenReturn(true);

        // Act
        List<String> kittyProducts = kittyProductService.getKittyProducts();

        // Assert
        assertNotNull(kittyProducts);
        assertEquals(3, kittyProducts.size());
        assertTrue(kittyProducts.contains("Catnip Toys"));
        assertTrue(kittyProducts.contains("Laser Pointers"));
        assertTrue(kittyProducts.contains("Space-themed Scratching Posts"));
    }

    @Test
    public void testGetKittyProducts_featureDisabled() {
        // Arrange: ensure the feature toggle is disabled
        Mockito.when(featureToggleService.isFeatureEnabled(FeatureToggleEnum.KITTY_PRODUCTS.getToggleName())).thenReturn(false);

        // Act & Assert
        FeatureNotAvailableException thrown = assertThrows(FeatureNotAvailableException.class, () -> {
            kittyProductService.getKittyProducts();
        });

        assertEquals("Feature kittyProducts is not enabled!", thrown.getMessage());
    }
}

