package com.cosmocats.api.web;

import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductService productService;

    private UUID productId;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        // Initialize productId and productDto before each test
        productId = UUID.randomUUID();
        productDto = ProductDto.builder()
                .id(productId)
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(BigDecimal.valueOf(10.50))
                .category(null) // Assuming category is not used for this example
                .build();
    }

    @Test
    void createProduct() throws Exception {
        // Create ProductEntryDto for the request
        ProductEntryDto entryDto = ProductEntryDto.builder()
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(new BigDecimal("10.50"))
                .category(null)
                .build();

        // Return the created ProductDto when the service is called
        ProductDto createdProductDto = ProductDto.builder()
                .id(productId)
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(new BigDecimal("10.50"))
                .category(null)
                .build();

        when(productService.createProduct(entryDto)).thenReturn(createdProductDto);

        // Perform the POST request and validate the response
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Galaxy Milk")))
                .andExpect(jsonPath("$.price", is(10.50)))
                .andExpect(jsonPath("$.id", is(productId.toString())));  // Compare UUID as String
    }

    @Test
    void updateProduct() throws Exception {
        // Create ProductUpdateDto for the update
        ProductUpdateDto updateDto = ProductUpdateDto.builder()
                .name("Updated Galaxy Milk")
                .description("Even better milk from space cows")
                .price(new BigDecimal("11.00"))
                .build();

        // Mock the updated product returned by the service
        ProductDto updatedProductDto = ProductDto.builder()
                .id(productId)
                .name("Updated Galaxy Milk")
                .description("Even better milk from space cows")
                .price(new BigDecimal("11.00"))
                .category(null)
                .build();

        when(productService.updateProduct(productId, updateDto)).thenReturn(updatedProductDto);

        // Perform the PUT request and validate the response
        mockMvc.perform(put("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Galaxy Milk")))
                .andExpect(jsonPath("$.price", is(11.00)))
                .andExpect(jsonPath("$.id", is(productId.toString())));  // Compare UUID as String
    }

    @Test
    void getProduct_ShouldReturnProduct() throws Exception {
        // Mock the product returned by the service
        when(productService.getProductById(productId)).thenReturn(productDto);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/v1/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(productId.toString())))  // Compare UUID as String
                .andExpect(jsonPath("$.name", is("Galaxy Milk")))
                .andExpect(jsonPath("$.price", is(10.50)));
    }

    @Test
    void getAllProducts_ShouldReturnProductList() throws Exception {
        // Create two products for the list
        ProductDto product1 = ProductDto.builder()
                .id(UUID.randomUUID())
                .name("Galaxy Milk")
                .description("Delicious milk from space cows")
                .price(BigDecimal.valueOf(10.50))
                .category(null)
                .build();

        ProductDto product2 = ProductDto.builder()
                .id(UUID.randomUUID())
                .name("Star Yarn")
                .description("Anti-gravity yarn for cosmic cats")
                .price(BigDecimal.valueOf(20.00))
                .category(null)
                .build();

        // Mock the list returned by the service
        when(productService.getAllProducts()).thenReturn(List.of(product1, product2));

        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(product1.getId().toString())))  // Compare UUID as String
                .andExpect(jsonPath("$[1].id", is(product2.getId().toString())));  // Compare UUID as String
    }
}
