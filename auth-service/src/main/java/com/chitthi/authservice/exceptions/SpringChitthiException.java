package com.chitthi.authservice.exceptions;

public class SpringChitthiException extends RuntimeException {
    public SpringChitthiException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringChitthiException(String exMessage) {
        super(exMessage);
    }
}
