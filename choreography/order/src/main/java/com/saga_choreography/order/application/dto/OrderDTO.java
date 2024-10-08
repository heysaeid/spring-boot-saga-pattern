package com.saga_choreography.order.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    private String province;
    private String city;
    private List<OrderItemDTO> orderItems;
}