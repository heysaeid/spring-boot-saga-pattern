package com.saga_choreography.delivery.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignDeliveryToPersonEventDTO {
    private Long deliveryId;
}
