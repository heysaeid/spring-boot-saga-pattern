package com.saga_choreography.order.application.mapper;

import com.saga_choreography.order.application.dto.CreateDeliveryEventDTO;
import com.saga_choreography.order.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateDeliveryEventMapper {
    @Mapping(source = "id", target = "orderId")
    CreateDeliveryEventDTO orderToCreateDeliveryEventDTO(Order order);
}
