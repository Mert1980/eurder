package com.switchfully.order.exception;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String message) {
        super(message);
    }
}
