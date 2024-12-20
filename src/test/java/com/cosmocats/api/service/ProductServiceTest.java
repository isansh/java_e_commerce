package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.domain.Product;
import com.cosmocats.api.web.mapper.ProductMapper;
import com.cosmocats.api.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private ProductEntryDto testEntryDto;
    private ProductDto testProductDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testProduct = Product.builder()
                .id(1L)
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .category(null) // Категорія може бути null для тесту
                .build();

        testEntryDto = ProductEntryDto.builder()
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .category(null)
                .build();

        testProductDto = ProductDto.builder()
                .id(1L)
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .category(null)
                .build();
    }

    @Test
    void createProduct() {
        when(productMapper.toProductDto(testProduct)).thenReturn(testProductDto);

        ProductDto createdProduct = productService.createProduct(testEntryDto);

        assertNotNull(createdProduct);
        assertEquals("Cosmic Candy", createdProduct.getName());
        verify(productMapper, times(1)).toProductDto(any(Product.class));
    }

    @Test
    void getAllProducts() {
        productService.createProduct(testEntryDto);

        when(productMapper.toProductDtoList(anyList())).thenReturn(List.of(testProductDto));

        List<ProductDto> products = productService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Cosmic Candy", products.get(0).getName());
        verify(productMapper, times(1)).toProductDtoList(anyList());
    }

    @Test
    void getProductById() {
        System.out.println("In-memory database size: " + productService.getInMemoryDatabaseSize());
        productService.createProduct(testEntryDto);

        // Перевірка кількості продуктів в пам'яті
        assertEquals(1, productService.getInMemoryDatabaseSize());

        ProductDto product = productService.getProductById(1L);

        assertNotNull(product);
        assertEquals("Cosmic Candy", product.getName());
    }



    @Test
    void getProductByIdShouldThrowExceptionIfNotFound() {
        assertThrows(NoSuchElementException.class, () -> productService.getProductById(999L));
    }

    @Test
    void updateProduct() {
        productService.createProduct(testEntryDto);

        ProductEntryDto updatedEntryDto = ProductEntryDto.builder()
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        ProductDto updatedProductDto = ProductDto.builder()
                .id(1L)
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        when(productMapper.toProductDto(any(Product.class))).thenReturn(updatedProductDto);

        ProductDto updatedProduct = productService.updateProduct(1L, updatedEntryDto);

        assertNotNull(updatedProduct);
        assertEquals("Updated Cosmic Candy", updatedProduct.getName());
    }

    @Test
    void updateProductShouldThrowExceptionIfNotFound() {
        ProductEntryDto updatedEntryDto = ProductEntryDto.builder()
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        assertThrows(NoSuchElementException.class, () -> productService.updateProduct(999L, updatedEntryDto));
    }

    @Test
    void deleteProduct() {
        productService.createProduct(testEntryDto);

        assertDoesNotThrow(() -> productService.deleteProduct(1L));
    }

    @Test
    void deleteProductShouldThrowExceptionIfNotFound() {
        assertThrows(NoSuchElementException.class, () -> productService.deleteProduct(999L));
    }
}
