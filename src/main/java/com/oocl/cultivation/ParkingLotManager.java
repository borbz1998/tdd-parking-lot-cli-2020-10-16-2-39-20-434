package com.oocl.cultivation;

import com.oocl.cultivation.behavior.ParkingBoyBehavior;
import com.oocl.cultivation.interfaces.IParkingBoy;

import java.util.List;

public class ParkingLotManager extends ParkingBoyBehavior implements IParkingBoy {

    public ParkingLotManager(List<ParkingLot> parkingLotList) {
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

    public List<IParkingBoy> addNewParkingBoy(ParkingBoyList parkingBoyList, IParkingBoy newParkingBoy) {
        parkingBoyList.getParkingBoyList().add(newParkingBoy);
        return parkingBoyList.getParkingBoyList();
    }
}
