package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto createProduct(ProductEntryDto productEntryDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(UUID id);
    ProductDto updateProduct(UUID id, ProductUpdateDto productUpdateDto);
    void deleteProduct(UUID id);
}
