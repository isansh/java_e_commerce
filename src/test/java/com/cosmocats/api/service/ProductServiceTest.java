package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.domain.Product;
import com.cosmocats.api.web.mapper.ProductMapper;
import com.cosmocats.api.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ProductServiceImpl.class})
class ProductServiceTest {

    @MockBean
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private ProductEntryDto testEntryDto;
    private ProductDto testProductDto;

    @BeforeEach
    void setUp() {
        UUID productId = UUID.randomUUID(); // Генерація UUID для продукту
        testProduct = Product.builder()
                .id(productId)
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .category(null)
                .build();

        testEntryDto = ProductEntryDto.builder()
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .category(null)
                .build();

        testProductDto = ProductDto.builder()
                .id(productId) // Використовуємо UUID
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
        productService.createProduct(testEntryDto);


        assertEquals(1, productService.getInMemoryDatabaseSize());

        ProductDto product = productService.getProductById(testProduct.getId());

        // Перевірка на те, чи продукт існує
        assertNotNull(product);
        assertEquals("Cosmic Candy", product.getName());
    }


    @Test
    void getProductByIdShouldThrowExceptionIfNotFound() {
        assertThrows(NoSuchElementException.class, () -> productService.getProductById(UUID.randomUUID()));
    }

    @Test
    void updateProduct() {
        productService.createProduct(testEntryDto);

        ProductUpdateDto updatedEntryDto = ProductUpdateDto.builder()
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        ProductDto updatedProductDto = ProductDto.builder()
                .id(testProduct.getId()) // Використовуємо UUID
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        when(productMapper.toProductDto(any(Product.class))).thenReturn(updatedProductDto);

        ProductDto updatedProduct = productService.updateProduct(testProduct.getId(), updatedEntryDto);

        assertNotNull(updatedProduct);
        assertEquals("Updated Cosmic Candy", updatedProduct.getName());
    }

    @Test
    void updateProductShouldThrowExceptionIfNotFound() {
        ProductUpdateDto updatedUpdateDto = ProductUpdateDto.builder()
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .category(null)
                .build();

        assertThrows(NoSuchElementException.class, () -> productService.updateProduct(UUID.randomUUID(), updatedUpdateDto));
    }

    @Test
    void deleteProduct() {
        productService.createProduct(testEntryDto);

        assertDoesNotThrow(() -> productService.deleteProduct(testProduct.getId()));
    }

    @Test
    void deleteProductShouldThrowExceptionIfNotFound() {
        assertThrows(NoSuchElementException.class, () -> productService.deleteProduct(UUID.randomUUID()));
    }
}
