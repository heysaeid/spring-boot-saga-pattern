package com.saga_choreography.order.application.dto;
import lombok.Data;

@Data
public class CreatePaymentEventDTO {
    private Long orderId;
    public Float amount;
}
