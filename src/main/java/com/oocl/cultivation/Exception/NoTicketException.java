package com.oocl.cultivation;

public class NoTicketException extends RuntimeException{
    public NoTicketException() {
        super("Please provide your parking ticket!");
    }
}
