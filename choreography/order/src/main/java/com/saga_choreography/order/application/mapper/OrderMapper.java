package com.saga_choreography.order.application.mapper;

import com.saga_choreography.order.application.dto.OrderItemDTO;
import com.saga_choreography.order.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.saga_choreography.order.application.dto.OrderDTO;
import com.saga_choreography.order.domain.entity.Order;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    //@Mapping(target = "orderItems", ignore=true)
    OrderDTO orderToOrderDTO(Order order);
    //@Mapping(target = "orderItems", ignore=true)
    Order orderDTOToOrder(OrderDTO orderDTO);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);
}
