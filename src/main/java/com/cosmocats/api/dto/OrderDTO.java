package com.cosmocats.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class OrderDTO {
    long id;
    List<ProductDto> products;
    LocalDateTime orderDate;
    String customerName;
}

