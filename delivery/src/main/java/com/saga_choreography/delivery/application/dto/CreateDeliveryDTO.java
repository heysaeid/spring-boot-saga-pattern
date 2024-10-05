package com.saga_choreography.delivery.application.dto;

import lombok.Data;

@Data
public class CreateDeliveryDTO {
    private Long orderId;
    private String province;
    private String city;
}