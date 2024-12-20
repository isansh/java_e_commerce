package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Category {
    long id;
    String name;
    String description;

    public Category(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
