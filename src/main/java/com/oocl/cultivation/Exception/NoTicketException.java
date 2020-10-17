package com.oocl.cultivation.Exception;

public class NoTicketException extends RuntimeException{
    public NoTicketException() {
        super("Please provide your parking ticket!");
    }
}
