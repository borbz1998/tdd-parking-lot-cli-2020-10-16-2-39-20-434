package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.SmartParkingBoyBehavior;

import java.util.List;

public class SmartParkingBoy extends SmartParkingBoyBehavior {
    private List<Integer> parkingLotMapEmptyPosition;
    private int index = 0;

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        return super.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

}
