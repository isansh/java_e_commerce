package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class Product {
    long id;
    String name;
    String description;
    BigDecimal price;
    Category category;
}

