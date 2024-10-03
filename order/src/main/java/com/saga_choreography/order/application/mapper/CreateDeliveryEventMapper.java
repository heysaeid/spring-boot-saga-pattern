package com.saga_choreography.order.application.mapper;

import com.saga_choreography.order.application.dto.CreateDeliveryEventDTO;
import com.saga_choreography.order.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateDeliveryEventMapper {
    CreateDeliveryEventDTO orderToCreateDeliveryEventDTO(Order order);
}
