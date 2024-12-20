package com.cosmocats.api.web.mapper;

import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.domain.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductEntryDto productEntryDto);

    Product toProduct(ProductUpdateDto productUpdateDto);

    ProductDto toProductDto(Product product); // Для перетворення з Product у ProductDto

    ProductEntryDto toProductEntryDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> products); // Для списків
}

