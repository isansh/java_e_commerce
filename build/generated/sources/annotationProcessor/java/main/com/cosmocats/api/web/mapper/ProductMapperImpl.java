package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Product;
import com.cosmocats.api.dto.ProductDto;
import com.cosmocats.api.dto.ProductEntryDto;
import com.cosmocats.api.dto.ProductUpdateDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T12:10:34+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductEntryDto productEntryDto) {
        if ( productEntryDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productEntryDto.getName() );
        product.description( productEntryDto.getDescription() );
        product.price( productEntryDto.getPrice() );
        product.category( productEntryDto.getCategory() );

        return product.build();
    }

    @Override
    public Product toProduct(ProductUpdateDto productUpdateDto) {
        if ( productUpdateDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productUpdateDto.getName() );
        product.description( productUpdateDto.getDescription() );
        product.price( productUpdateDto.getPrice() );
        product.category( productUpdateDto.getCategory() );

        return product.build();
    }

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.name( product.getName() );
        productDto.description( product.getDescription() );
        productDto.price( product.getPrice() );
        productDto.category( product.getCategory() );

        return productDto.build();
    }

    @Override
    public ProductEntryDto toProductEntryDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntryDto.ProductEntryDtoBuilder productEntryDto = ProductEntryDto.builder();

        productEntryDto.name( product.getName() );
        productEntryDto.description( product.getDescription() );
        productEntryDto.price( product.getPrice() );
        productEntryDto.category( product.getCategory() );

        return productEntryDto.build();
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }
}
