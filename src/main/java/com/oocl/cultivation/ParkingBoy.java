package com.oocl.cultivation;

import com.oocl.cultivation.behavior.ParkingBoyBehavior;
import com.oocl.cultivation.interfaces.IParkingBoy;

import java.util.List;

public class ParkingBoy extends ParkingBoyBehavior implements IParkingBoy {

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        return super.park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        return super.fetch(parkingTicket);
    }
}
