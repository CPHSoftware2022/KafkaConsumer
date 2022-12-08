package com.example.kafkaconsumer.model;

import org.springframework.http.HttpStatus;

public class EventModel {

    private String method;
    private String status;
    private String responseBody;

    public EventModel() {
    }

    public EventModel(String method, String status, String responseBody) {
        this.method = method;
        this.status = status;
        this.responseBody = responseBody;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "method='" + method + '\'' +
                ", status=" + status +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
