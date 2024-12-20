package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
@Getter
public class Product {
    long id;
    String name;
    String description;
    BigDecimal price;
    Category category;

    public Product(long id, String name, String description, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}

