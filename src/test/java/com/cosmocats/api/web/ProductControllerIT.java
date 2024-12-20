package com.cosmocats.api.web;

import com.cosmocats.api.web.ProductController;
import com.cosmocats.api.domain.Category;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.service.ProductService;
import com.cosmocats.api.web.mapper.ProductMapper;
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
    public void createProduct_ShouldReturnCreatedProduct() throws Exception {
        ProductEntryDto productEntryDto = new ProductEntryDto("Galaxy Comet Milk", "Delicious cosmic milk", new BigDecimal("5.99"), new Category("Dairy"));


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productEntryDto)))
                .andExpect(status().isCreated()) // expect 201 status
                .andExpect(jsonPath("$.name").value("Galaxy Comet Milk"))
                .andExpect(jsonPath("$.price").value(5.99))
                .andExpect(jsonPath("$.weight").value(2.5));
    }



    @Test
    public void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        Long productId = 1L;
        ProductUpdateDto productUpdateDto = new ProductUpdateDto("Starry Moon Updated Milk", "Updated cosmic milk with a new flavor", new BigDecimal("6.99"), new BigDecimal("3.0"));

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdateDto)))
                .andExpect(status().isOk()) // expect 200 status
                .andExpect(jsonPath("$.name").value("Starry Moon Updated Milk"))
                .andExpect(jsonPath("$.price").value(6.99))
                .andExpect(jsonPath("$.weight").value(3.0));
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
