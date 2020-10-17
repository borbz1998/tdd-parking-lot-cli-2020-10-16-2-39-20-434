package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;

import java.util.List;

public class ParkingBoy extends ParkingBoyBehavior {

    public ParkingBoy(ParkingLot parkingLot) {
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
