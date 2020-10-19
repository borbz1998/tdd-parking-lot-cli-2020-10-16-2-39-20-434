package com.oocl.cultivation.exception;

public class NoParkingLotSpaceException extends RuntimeException {
    public NoParkingLotSpaceException() {
        super("Not Enough Position.");
    }
}
