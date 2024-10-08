package com.saga_choreography.order.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDeliveryEventDTO {
    private Long orderId;
    private Long paymentId;
    private String province;
    private String city;
}
