package com.switchfully.order.exception;

public class NotAvailableStockException extends RuntimeException{
    public NotAvailableStockException(String message) {
        super(message);
    }
}
