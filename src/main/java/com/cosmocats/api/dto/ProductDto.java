package com.cosmocats.api.dto;

import com.cosmocats.api.domain.Category;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class ProductDto {
    long id;
    String name;
    String description;
    BigDecimal price;
    Category category;
}