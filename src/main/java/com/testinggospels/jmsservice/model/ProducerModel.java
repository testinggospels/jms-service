package com.testinggospels.jmsservice.model;

public class ProducerModel {
    private String message;

    public ProducerModel() {
    }

    public ProducerModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}