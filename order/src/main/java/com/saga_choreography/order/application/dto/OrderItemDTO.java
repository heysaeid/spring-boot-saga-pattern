package com.saga_choreography.order.application.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
}