package com.oocl.cultivation;

import com.oocl.cultivation.behavior.SmartParkingBoyBehavior;
import com.oocl.cultivation.interfaces.IParkingBoy;

import java.util.List;

public class SmartParkingBoy extends SmartParkingBoyBehavior implements IParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
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
