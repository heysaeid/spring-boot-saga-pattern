package com.saga_choreography.payment.application.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendMessage(KafkaProducerTopics topic, Object message) {
        kafkaTemplate.send(topic.getTopicName(), message);
        logger.info(
                String.format("Sending message to kafka topic %s: %s", topic, message)
        );
    }

}
