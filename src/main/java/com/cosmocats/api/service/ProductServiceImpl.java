package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.domain.Product;
import com.cosmocats.api.web.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final Map<Long, Product> inMemoryDatabase = new HashMap<>();
    private Long productIdSequence = 1L;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createProduct(ProductEntryDto productEntryDto) {
        Product product = productMapper.toProduct(productEntryDto)
                .toBuilder()
                .id(productIdSequence++)
                .build();
        inMemoryDatabase.put(product.getId(), product);
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = new ArrayList<>(inMemoryDatabase.values());
        return productMapper.toProductDtoList(products);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = inMemoryDatabase.get(id);
        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product existingProduct = inMemoryDatabase.get(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        Product updatedProduct = productMapper.toProduct(productUpdateDto)
                .toBuilder()
                .id(id)
                .build();
        inMemoryDatabase.put(id, updatedProduct);
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!inMemoryDatabase.containsKey(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        inMemoryDatabase.remove(id);
    }
}
