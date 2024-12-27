package com.cosmocats.api.service;

import com.cosmocats.api.domain.Product;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.web.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductServiceImpl implements ProductService { // Реалізація інтерфейсу ProductService
    private final Map<UUID, Product> inMemoryDatabase = new ConcurrentHashMap<>();
    private final ProductMapper productMapper;
    private long productIdSequence = 1;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public int getInMemoryDatabaseSize() {
        return inMemoryDatabase.size();
    }

    @Override
    public ProductDto createProduct(ProductEntryDto productEntryDto) {
        Product product = Product.builder()
                .id(UUID.randomUUID()) // Генерація ID
                .name(productEntryDto.getName())
                .description(productEntryDto.getDescription())
                .price(productEntryDto.getPrice())
                .category(productEntryDto.getCategory())
                .build();

        inMemoryDatabase.put(product.getId(), product); // Збереження продукту
        return productMapper.toProductDto(product); // Перетворення на DTO
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = new ArrayList<>(inMemoryDatabase.values());
        return productMapper.toProductDtoList(products); // Перетворення списку на DTO
    }

    @Override
    public ProductDto getProductById(UUID id) {
        Product product = inMemoryDatabase.get(id);
        if (product == null) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Product with ID ").append(id).append(" not found.");
            throw new NoSuchElementException(errorMessage.toString());
        }
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(UUID id, ProductUpdateDto productUpdateDto) {
        Product existingProduct = inMemoryDatabase.get(id);
        if (existingProduct == null) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Product with ID ").append(id).append(" not found.");
            throw new NoSuchElementException(errorMessage.toString());
        }

        Product updatedProduct = Product.builder()
                .id(id)
                .name(productUpdateDto.getName()) // Оновлені поля з ProductUpdateDto
                .description(productUpdateDto.getDescription())
                .price(productUpdateDto.getPrice())
                .category(productUpdateDto.getCategory())
                .build();

        inMemoryDatabase.put(id, updatedProduct);
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        if (!inMemoryDatabase.containsKey(id)) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Product with ID ").append(id).append(" not found.");
            throw new NoSuchElementException(errorMessage.toString());
        }
        inMemoryDatabase.remove(id);
    }
}
