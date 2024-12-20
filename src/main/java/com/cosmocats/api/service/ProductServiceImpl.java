package com.cosmocats.api.service;

import com.cosmocats.api.domain.Product;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.web.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductServiceImpl {
    private final Map<Long, Product> inMemoryDatabase = new ConcurrentHashMap<>();
    private final ProductMapper productMapper;
    private long productIdSequence = 1;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public int getInMemoryDatabaseSize() {
        return inMemoryDatabase.size();
    }

    // Створення продукту
    public ProductDto createProduct(ProductEntryDto productEntryDto) {
        Product product = Product.builder()
                .id(productIdSequence++) // Генерація ID
                .name(productEntryDto.getName())
                .description(productEntryDto.getDescription())
                .price(productEntryDto.getPrice())
                .category(productEntryDto.getCategory())
                .build();

        inMemoryDatabase.put(product.getId(), product); // Збереження продукту
        return productMapper.toProductDto(product); // Перетворення на DTO
    }

    // Отримання списку продуктів
    public List<ProductDto> getAllProducts() {
        List<Product> products = new ArrayList<>(inMemoryDatabase.values());
        return productMapper.toProductDtoList(products); // Перетворення списку на DTO
    }

    // Отримання продукту за ID
    public ProductDto getProductById(Long id) {
        Product product = inMemoryDatabase.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product with ID " + id + " not found.");
        }
        return productMapper.toProductDto(product);
    }

    // Оновлення продукту
    public ProductDto updateProduct(Long id, ProductEntryDto productEntryDto) {
        Product existingProduct = inMemoryDatabase.get(id);
        if (existingProduct == null) {
            throw new NoSuchElementException("Product with ID " + id + " not found.");
        }

        Product updatedProduct = Product.builder()
                .id(id)
                .name(productEntryDto.getName())
                .description(productEntryDto.getDescription())
                .price(productEntryDto.getPrice())
                .category(productEntryDto.getCategory())
                .build();

        inMemoryDatabase.put(id, updatedProduct);
        return productMapper.toProductDto(updatedProduct);
    }

    // Видалення продукту
    public void deleteProduct(Long id) {
        if (!inMemoryDatabase.containsKey(id)) {
            throw new NoSuchElementException("Product with ID " + id + " not found.");
        }
        inMemoryDatabase.remove(id);
    }
}
