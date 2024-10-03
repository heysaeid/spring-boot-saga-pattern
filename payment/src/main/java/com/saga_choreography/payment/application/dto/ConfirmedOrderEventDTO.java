package com.saga_choreography.payment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmedOrderEventDTO {
    private Long orderId;
}
