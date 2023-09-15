package com.ocr.applinhkien.model;

public class ResponeObject {
    private boolean success;
    private String message;

    public ResponeObject(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponeObject() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
