package com.cosmocats.api.domain;

import lombok.Builder;
import lombok.Value;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Review {
    @NotNull(message = "Product ID cannot be null")
    UUID productId;

    @NotBlank(message = "Reviewer name cannot be blank")
    @Size(max = 50, message = "Reviewer name must not exceed 50 characters")
    String reviewerName;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    Integer rating;

    @Size(max = 500, message = "Comment must not exceed 500 characters")
    String comment;
}
