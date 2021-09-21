package com.testinggospels.jmsservice.model;

public class DynamicProducerModel {
    private String brokers;
    private String message;

    public DynamicProducerModel() {
    }

    public DynamicProducerModel(String brokers, String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBrokers() {
        return this.brokers;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

}
