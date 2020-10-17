package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.SmartParkingBoyBehavior;
import com.oocl.cultivation.Interface.IParkingBoy;

public class SmartParkingBoy extends SmartParkingBoyBehavior implements IParkingBoy {

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
