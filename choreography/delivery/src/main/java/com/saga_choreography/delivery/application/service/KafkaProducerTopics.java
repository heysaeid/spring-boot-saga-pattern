package com.saga_choreography.delivery.application.service;


public enum KafkaProducerTopics {
    CANCEL_PAYMENT("cancel-payment"),
    COMPLETE_ORDER("complete-order"),
    ASSIGN_DELIVERY_TO_PERSON("assign-delivery-to-person");

    private final String topicName;

    KafkaProducerTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
