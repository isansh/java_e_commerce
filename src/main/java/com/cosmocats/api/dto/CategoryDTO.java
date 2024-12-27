package com.cosmocats.api.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CategoryDTO {
    UUID id;
    String name;
    String description;
}
