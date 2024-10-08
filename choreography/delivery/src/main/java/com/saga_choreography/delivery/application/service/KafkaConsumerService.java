package com.saga_choreography.delivery.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga_choreography.delivery.application.dto.AssignDeliveryToPersonEventDTO;
import com.saga_choreography.delivery.application.dto.CreateDeliveryDTO;
import com.saga_choreography.delivery.domain.entity.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private static final String CREATE_DELIVERY = "create-delivery";
    private static final String ASSIGN_DELIVERY_TO_PERSON = "assign-delivery-to-person";

    @KafkaListener(topics = CREATE_DELIVERY, groupId = CREATE_DELIVERY + "-grp")
    public void consumeCreateDelivery(String message) {
        try {
            CreateDeliveryDTO createDeliveryDTO = objectMapper.readValue(message, CreateDeliveryDTO.class);
            Delivery delivery = deliveryService.createDelivery(createDeliveryDTO);
            logger.info("New delivery created: {}", delivery);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize message: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = ASSIGN_DELIVERY_TO_PERSON, groupId = ASSIGN_DELIVERY_TO_PERSON + "-grp")
    public void consumeAssignDeliveryToPerson(String message) {
        try {
            AssignDeliveryToPersonEventDTO assignDeliveryToPersonEventDTO = objectMapper.readValue(message, AssignDeliveryToPersonEventDTO.class);
            Boolean isAssigned = deliveryService.assignDeliveryToPerson(assignDeliveryToPersonEventDTO.getDeliveryId());
            logger.info("isAssigned: {}", isAssigned);
        } catch (Exception e) {
            logger.error("Failed to deserialize message: {}", e.getMessage());
        }
    }

}
