package com.cosmocats.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OrderDTO {
    UUID id;
    List<ProductDto> products;
    LocalDateTime orderDate;
    String customerName;
}

