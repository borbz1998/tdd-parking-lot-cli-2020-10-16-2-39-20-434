package com.oocl.cultivation.exception;

public class WrongTicketException extends RuntimeException {
    public WrongTicketException() {
        super("Unrecognized Parking Ticket!");
    }
}
