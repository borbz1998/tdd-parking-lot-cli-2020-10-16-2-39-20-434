package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;
import com.oocl.cultivation.Interface.IParkingBoy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager extends ParkingBoyBehavior implements IParkingBoy {
    private ParkingBoyList parkingBoyList;
    private List<IParkingBoy> listOfParkingBoy;

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        return super.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

    public List<IParkingBoy> addNewParkingBoy(ParkingBoyList parkingBoyList, IParkingBoy newParkingBoy){
        parkingBoyList.getParkingBoyList().add(newParkingBoy);
        return parkingBoyList.getParkingBoyList();
    }
}
