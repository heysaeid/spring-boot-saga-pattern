package com.saga_choreography.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(KafkaProducerTopics topic, Object message) throws JsonProcessingException {
        String jsonString = new ObjectMapper().writeValueAsString(message);
        kafkaTemplate.send(topic.getTopicName(), message);
        logger.info(
            String.format("Sending message to Kafka topic %s: %s", topic.getTopicName(), message)
        );
    }
}

