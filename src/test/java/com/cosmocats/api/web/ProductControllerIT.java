package com.cosmocats.api.web;

import com.cosmocats.api.web.ProductController;
import com.cosmocats.api.domain.Category;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerIT {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductController productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void createProduct() {
        ProductEntryDto entryDto = ProductEntryDto.builder()
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(new BigDecimal("10.50"))
                .category(new Category(1L, "Dairy", "Milk products"))
                .build();

        ProductDto createdProductDto = ProductDto.builder()
                .id(1L)
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(new BigDecimal("10.50"))
                .category(new Category(1L, "Dairy", "Milk products"))
                .build();

        when(productService.createProduct(any(ProductEntryDto.class))).thenReturn(createdProductDto);

        ProductDto result = productService.createProduct(entryDto);

        assertNotNull(result);
        assertEquals("Galaxy Milk", result.getName());
        assertEquals(new BigDecimal("10.50"), result.getPrice());
    }

    @Test
    void updateProduct() {
        ProductUpdateDto updateDto = ProductUpdateDto.builder()
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

        when(productService.updateProduct(eq(1L), any(ProductUpdateDto.class))).thenReturn(updatedProductDto);

        ProductDto result = productService.updateProduct(1L, updateDto);

        assertNotNull(result);
        assertEquals("Updated Cosmic Candy", result.getName());
        assertEquals(new BigDecimal("10.99"), result.getPrice());
    }


    @Test
    void getProduct_ShouldReturnProduct() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(BigDecimal.valueOf(10.50))
                .category(new Category(1L, "Dairy", "Milk products"))
                .build();

        when(productService.getProductById(1L)).thenReturn(productDto);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Galaxy Milk")))
                .andExpect(jsonPath("$.description", is("Delicious milk from space cows")))
                .andExpect(jsonPath("$.price", is(10.50)))
                .andExpect(jsonPath("$.category.id", is(1)))
                .andExpect(jsonPath("$.category.name", is("Dairy")))
                .andExpect(jsonPath("$.category.description", is("Milk products")));
    }

    @Test
    void getAllProducts_ShouldReturnProductList() throws Exception {
        ProductDto product1Dto = ProductDto.builder()
                .id(1L)
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(BigDecimal.valueOf(10.50))
                .category(new Category(1L, "Dairy", "Milk products"))
                .build();

        ProductDto product2Dto = ProductDto.builder()
                .id(2L)
                .name("Star Yarn")
                .description("Anti-gravity yarn for cosmic cats")
                .price(BigDecimal.valueOf(20.00))
                .category(new Category(1L, "Dairy", "Milk products"))
                .build();

        when(productService.getAllProducts()).thenReturn(List.of(product1Dto, product2Dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
}
