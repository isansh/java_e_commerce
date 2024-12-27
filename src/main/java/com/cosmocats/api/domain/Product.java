package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Product {
    UUID id;
    String name;
    String description;
    BigDecimal price;
    Category category;
}

