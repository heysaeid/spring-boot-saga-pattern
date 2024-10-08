package com.saga_choreography.payment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelOrderEventDTO {
    private Long orderId;
}
