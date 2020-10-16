package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car, parkingLot.getParkingLotMapLists());
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }
}
