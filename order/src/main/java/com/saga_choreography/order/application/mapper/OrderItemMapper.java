package com.saga_choreography.order.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saga_choreography.order.application.dto.OrderItemDTO;
import com.saga_choreography.order.domain.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);
}
