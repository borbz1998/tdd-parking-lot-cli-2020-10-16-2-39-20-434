package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.SuperSmartParkingBehavior;
import com.oocl.cultivation.Interface.IParkingBoy;

public class SuperSmartParkingBoy extends SuperSmartParkingBehavior implements IParkingBoy {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        return super.park(car, iParkingBoy, parkingLotList);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }
}
