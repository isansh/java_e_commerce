package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Order;
import com.cosmocats.api.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}

