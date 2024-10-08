package com.saga_choreography.delivery.application.mapper;

import com.saga_choreography.delivery.application.dto.CreateDeliveryDTO;
import com.saga_choreography.delivery.domain.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateDeliveryMapper {
    Delivery createDeliveryDTOToDelivery(CreateDeliveryDTO createDeliveryDTO);

    CreateDeliveryDTO deliveryTocreateDeliveryDTO(Delivery delivery);
}
