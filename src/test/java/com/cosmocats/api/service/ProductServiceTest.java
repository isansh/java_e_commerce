package com.cosmocats.api.service;

import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.domain.Product;
import com.cosmocats.api.web.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private ProductEntryDto testEntryDto;
    private ProductUpdateDto testUpdateDto;
    private ProductDto testProductDto;

    @BeforeEach
    void setUp() {
        testProduct = Product.builder()
                .id(1L)
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .build();

        testEntryDto = ProductEntryDto.builder()
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .build();

        testUpdateDto = ProductUpdateDto.builder()
                .name("Updated Cosmic Candy")
                .description("Even tastier candy from the cosmos")
                .price(new BigDecimal("10.99"))
                .build();

        testProductDto = ProductDto.builder()
                .id(1L)
                .name("Cosmic Candy")
                .description("Delicious candy from the cosmos")
                .price(new BigDecimal("9.99"))
                .build();
    }

    @Test
    void createProduct() {
        when(productMapper.toProduct(testEntryDto)).thenReturn(testProduct);
        when(productMapper.toProductDto(testProduct)).thenReturn(testProductDto);

        ProductDto createdProduct = productService.createProduct(testEntryDto);

        assertNotNull(createdProduct);
        assertEquals("Cosmic Candy", createdProduct.getName());
        verify(productMapper, times(1)).toProduct(testEntryDto);
        verify(productMapper, times(1)).toProductDto(testProduct);
    }

    @Test
    void getAllProducts() {
        when(productMapper.toProductDtoList(anyList())).thenReturn(List.of(testProductDto));

        List<ProductDto> products = productService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Cosmic Candy", products.get(0).getName());
        verify(productMapper, times(1)).toProductDtoList(anyList());
    }

    @Test
    void getProductById() {
        when(productMapper.toProductDto(testProduct)).thenReturn(testProductDto);
        productService.createProduct(testEntryDto);

        ProductDto product = productService.getProductById(1L);

        assertNotNull(product);
        assertEquals("Cosmic Candy", product.getName());
        verify(productMapper, times(1)).toProductDto(testProduct);
    }

    @Test
    void getProductByIdShouldThrowExceptionIfNotFound() {
        assertThrows(RuntimeException.class, () -> productService.getProductById(999L));
    }

    @Test
    void updateProduct() {
        when(productMapper.toProduct(testUpdateDto)).thenReturn(testProduct);
        when(productMapper.toProductDto(testProduct)).thenReturn(testProductDto);

        ProductDto updatedProduct = productService.updateProduct(1L, testUpdateDto);

        assertNotNull(updatedProduct);
        assertEquals("Cosmic Candy", updatedProduct.getName());
        verify(productMapper, times(1)).toProduct(testUpdateDto);
        verify(productMapper, times(1)).toProductDto(testProduct);
    }

    @Test
    void updateProductShouldThrowExceptionIfNotFound() {
        assertThrows(RuntimeException.class, () -> productService.updateProduct(999L, testUpdateDto));
    }

    @Test
    void deleteProduct() {
        productService.createProduct(testEntryDto);

        assertDoesNotThrow(() -> productService.deleteProduct(1L));
    }

    @Test
    void deleteProductShouldThrowExceptionIfNotFound() {
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(999L));
    }
}
