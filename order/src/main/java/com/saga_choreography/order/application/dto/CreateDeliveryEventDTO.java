package com.saga_choreography.order.application.dto;

import lombok.Data;

@Data
public class CreateDeliveryEventDTO {
    private Long orderId;
    private String province;
    private String city;
}
