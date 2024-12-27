package com.cosmocats.api.web.mapper;

import com.cosmocats.api.domain.Order;
import com.cosmocats.api.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")  // Позначаємо маппер як Spring-компонент
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
