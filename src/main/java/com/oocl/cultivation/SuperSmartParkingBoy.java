package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.SuperSmartParkingBehavior;

public class SuperSmartParkingBoy extends SuperSmartParkingBehavior {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
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
