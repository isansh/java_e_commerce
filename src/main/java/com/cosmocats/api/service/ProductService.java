package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductEntryDto productEntryDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductUpdateDto productUpdateDto);
    void deleteProduct(Long id);
}
