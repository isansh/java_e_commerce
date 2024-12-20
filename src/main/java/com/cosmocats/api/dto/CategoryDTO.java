package com.cosmocats.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDTO {
    long id;
    String name;
    String description;
}
