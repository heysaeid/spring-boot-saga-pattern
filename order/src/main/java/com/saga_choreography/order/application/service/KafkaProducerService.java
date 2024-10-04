package com.saga_choreography.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(KafkaProducerTopics topic, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic.getTopicName(), jsonMessage);
            logger.info("Sending message to Kafka topic {}: {}", topic.getTopicName(), jsonMessage);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize message: {}", e.getMessage());
        }
    }
}

