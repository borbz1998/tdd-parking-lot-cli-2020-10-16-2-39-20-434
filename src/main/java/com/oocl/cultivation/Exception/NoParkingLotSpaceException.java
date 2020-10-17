package com.oocl.cultivation.Exception;

public class NoParkingLotSpaceException extends RuntimeException {
    public NoParkingLotSpaceException() {
        super("Not Enough Position.");
    }
}
