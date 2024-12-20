package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Order;
import com.cosmocats.api.domain.Product;
import com.cosmocats.api.dto.OrderDTO;
import com.cosmocats.api.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T12:10:34+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.id( order.getId() );
        orderDTO.products( productListToProductDtoList( order.getProducts() ) );
        orderDTO.orderDate( order.getOrderDate() );
        orderDTO.customerName( order.getCustomerName() );

        return orderDTO.build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDTO.getId() );
        order.products( productDtoListToProductList( orderDTO.getProducts() ) );
        order.orderDate( orderDTO.getOrderDate() );
        order.customerName( orderDTO.getCustomerName() );

        return order.build();
    }

    protected ProductDto productToProductDto(Product product) {
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

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.description( productDto.getDescription() );
        product.price( productDto.getPrice() );
        product.category( productDto.getCategory() );

        return product.build();
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }
}
