package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Order {
    UUID id;
    List<Product> products;
    LocalDateTime orderDate;
    String customerName;
}

