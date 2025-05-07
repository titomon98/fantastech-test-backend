package com.test.smsproject.exceptions;

public class SmsException extends RuntimeException {
    private final int statusCode;

    public SmsException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
