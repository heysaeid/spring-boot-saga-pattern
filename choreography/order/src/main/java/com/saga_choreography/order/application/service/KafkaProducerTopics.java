package com.saga_choreography.order.application.service;


public enum KafkaProducerTopics {
    CREATE_PAYMENT("create-payment"),
    CREATE_DELIVERY("create-delivery");

    private final String topicName;

    KafkaProducerTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
