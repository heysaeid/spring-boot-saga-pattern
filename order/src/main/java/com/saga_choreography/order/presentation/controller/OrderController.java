package com.saga_choreography.order.presentation.controller;


import com.saga_choreography.order.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga_choreography.order.application.dto.OrderDTO;
import com.saga_choreography.order.application.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

}

