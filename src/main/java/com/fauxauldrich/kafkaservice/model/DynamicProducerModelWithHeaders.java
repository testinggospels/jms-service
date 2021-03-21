package com.fauxauldrich.kafkaservice.model;

import java.util.Map;

public class DynamicProducerModelWithHeaders {
    private String brokers;
    private String message;
    private Map<String, String> headers;

    public DynamicProducerModelWithHeaders() {
    }

    public DynamicProducerModelWithHeaders(String brokers, String message, Map<String, String> headers) {
        this.message = message;
        this.headers = headers;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBrokers() {
        return this.brokers;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }
}
