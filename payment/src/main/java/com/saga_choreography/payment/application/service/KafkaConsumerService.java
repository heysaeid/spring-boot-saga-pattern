package com.saga_choreography.payment.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga_choreography.payment.application.dto.CancelPaymentDTO;
import com.saga_choreography.payment.application.dto.CreatePaymentEventDTO;
import com.saga_choreography.payment.domain.entity.Payment;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.serializer.StringOrBytesSerializer;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private static final String createPaymentTopic = "create-payment";
    private static final String cancelPaymentTopic = "cancel-payment";

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = createPaymentTopic, groupId = createPaymentTopic + "-grp")
    public void consumeCreatePayment(CreatePaymentEventDTO message) throws JsonProcessingException{
        String jsonString = new ObjectMapper().writeValueAsString(message);
        //CreatePaymentDTO paymentDTO = paymentService.createPayment(createPaymentDTO);
        logger.info("New payment created: " + message);
    }

    @KafkaListener(topics = cancelPaymentTopic, groupId = cancelPaymentTopic + "-grp")
    public void consumeCancelPayment(CancelPaymentDTO cancelPaymentDTO) throws JsonProcessingException {
        String jsonString = new ObjectMapper().writeValueAsString(cancelPaymentDTO);
        //Payment payment = paymentService.cancelPayment(cancelPaymentDTO.getOrderId());
        logger.info("The payment was cancelled: " + cancelPaymentDTO);
    }

}
