package com.saga_choreography.payment.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga_choreography.payment.application.dto.CancelPaymentDTO;
import com.saga_choreography.payment.application.dto.CreatePaymentDTO;
import com.saga_choreography.payment.domain.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private static final String createPaymentTopic = "create-payment";
    private static final String cancelPaymentTopic = "cancel-payment";

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = createPaymentTopic, groupId = createPaymentTopic + "-grp")
    public void consumeCreatePayment(String message) {
        try {
            CreatePaymentDTO createPaymentDTO = objectMapper.readValue(message, CreatePaymentDTO.class);
            paymentService.createPayment(createPaymentDTO);
            logger.info("New payment created: {}", createPaymentDTO);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize message: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = cancelPaymentTopic, groupId = cancelPaymentTopic + "-grp")
    public void consumeCancelPayment(String message) {
        try {
            CancelPaymentDTO cancelPaymentDTO = objectMapper.readValue(message, CancelPaymentDTO.class);
            Payment payment = paymentService.cancelPayment(cancelPaymentDTO.getOrderId());
            logger.info("The payment was cancelled: {}", payment);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize message {}", e.getMessage());
        }
    }

}
