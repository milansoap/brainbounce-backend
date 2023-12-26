package com.example.brainbounce.dto;

public class ApiError {
    private String message;

    public ApiError(String message) {
        this.message = message;
    }

    public ApiError() {
        this.message = "Unknown error";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
