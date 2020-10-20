package com.oocl.cultivation;

import java.util.List;

import static java.util.Collections.singletonList;

public class ParkingEmployee {

    public List<ParkingLot> parkingLotList;

    ParkingEmployee(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    ParkingEmployee(ParkingLot parkingLot) {
        this.parkingLotList = singletonList(parkingLot);
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

}
