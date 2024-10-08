package com.saga_choreography.payment.application.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePaymentDTO {
    private Long orderId;
    public Float amount;
}