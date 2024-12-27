package com.cosmocats.api.dto;

import com.cosmocats.api.domain.Category;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class ProductDto {
    UUID id;
    String name;
    String description;
    BigDecimal price;
    Category category;
}