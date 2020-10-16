package com.oocl.cultivation;

public class NoParkingLotSpaceException extends RuntimeException {
    public NoParkingLotSpaceException() {
        super("Not Enough Position.");
    }
}
