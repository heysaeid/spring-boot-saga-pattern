package com.saga_choreography.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga_choreography.order.application.dto.CancelOrderDTO;
import com.saga_choreography.order.domain.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private static final String CANCEL_ORDER_TOPIC = "cancel-order";

    @KafkaListener(topics = CANCEL_ORDER_TOPIC, groupId = CANCEL_ORDER_TOPIC + "-grp")
    public void consumeCancelOrder(String message) {
        try {
            CancelOrderDTO cancelOrderDTO = objectMapper.readValue(message, CancelOrderDTO.class);
            Order order = orderService.cancelOrder(cancelOrderDTO.getOrderId());
            logger.info("The order was cancelled: {}", order);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize message: {}", e.getMessage());
        }
    }

}
