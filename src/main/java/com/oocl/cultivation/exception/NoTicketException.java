package com.oocl.cultivation.exception;

public class NoTicketException extends RuntimeException {
    public NoTicketException() {
        super("Please provide your parking ticket!");
    }
}
