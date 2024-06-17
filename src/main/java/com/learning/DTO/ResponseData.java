package com.learning.DTO;

import java.util.HashMap;

public class ResponseData<T> {
    private boolean status;
    private HashMap<String, String> messages = new HashMap<>();
    private T payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
