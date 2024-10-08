package com.saga_choreography.delivery.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelPaymentEventDTO {
    private Long paymentId;
}
