package com.cosmocats.api.dto;

import com.cosmocats.api.domain.Product;
import com.cosmocats.api.dto.validation.CosmicWordCheck;
import com.cosmocats.api.domain.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProductEntryDto {

    @NotNull(message = "Product name is required.")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters.")
    @CosmicWordCheck(message = "Product name must include cosmic terms (e.g., 'galaxy', 'comet').")
    String name;

    @Size(max = 255, message = "Product description cannot exceed 255 characters.")
    @NotNull(message = "Product description is required.")
    @NotBlank(message = "Product description cannot be empty.")
    String description;

    @NotNull(message = "Product price is required.")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero.")
    @Digits(integer = 5, fraction = 2, message = "Price must be a number with up to 5 digits and 2 decimal places.")
    BigDecimal price;

    @NotNull(message = "Category is required.")
    Category category;

    public Product toProduct(Long id) {
        return new Product(id, name, description, price, category);
    }

}
