package com.saga_choreography.payment.application.service;


public enum KafkaProducerTopics {
    CONFIRMED_ORDER("confirmed-order"),
    CANCEL_ORDER("cancel-order");

    private final String topicName;

    KafkaProducerTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
