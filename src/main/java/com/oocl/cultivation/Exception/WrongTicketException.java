package com.oocl.cultivation;

public class WrongTicketException extends RuntimeException {
    public WrongTicketException() {
        super("Unrecognized Parking Ticket!");
    }
}