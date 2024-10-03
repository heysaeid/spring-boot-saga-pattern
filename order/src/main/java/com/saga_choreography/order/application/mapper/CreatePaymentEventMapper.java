package com.saga_choreography.order.application.mapper;


import com.saga_choreography.order.application.dto.CreatePaymentEventDTO;
import com.saga_choreography.order.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreatePaymentEventMapper {
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "totalAmount", target = "amount")
    CreatePaymentEventDTO orderToCreatePaymentEventDTO(Order order);
}
