package com.afperdomo2.pizzaya.service.exception;

public class EmailApiException extends RuntimeException {
    public EmailApiException() {
        super("❌ Error sending email...");
    }
}
