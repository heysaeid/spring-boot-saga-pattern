package com.saga_choreography.order.application.service;

import com.saga_choreography.order.application.dto.cancelOrderDTO;
import com.saga_choreography.order.domain.entity.Order;


import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private static final String CANCEL_ORDER_TOPIC = "cancel-order";

    @KafkaListener(topics = CANCEL_ORDER_TOPIC, groupId = CANCEL_ORDER_TOPIC + "-grp")
    public void consumeCancelOrder(cancelOrderDTO cancelOrderDTO) {
        Order order = orderService.cancelOrder(cancelOrderDTO.getOrderId());
        logger.info(
            String.format("The order was cancelled: " + order)
        );
    }

}
