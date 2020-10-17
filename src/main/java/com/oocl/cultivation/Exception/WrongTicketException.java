package com.oocl.cultivation.Exception;

public class WrongTicketException extends RuntimeException {
    public WrongTicketException() {
        super("Unrecognized Parking Ticket!");
    }
}
