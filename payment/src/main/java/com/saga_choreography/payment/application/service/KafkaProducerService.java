package com.saga_choreography.payment.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    public void sendMessage(KafkaProducerTopics topic, Object message) {
        try {
            String jsonString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic.getTopicName(), jsonString);
            logger.info("Sending message to kafka topic {}: {}", topic, jsonString);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize message: {}", e.getMessage());
        }
    }
}
