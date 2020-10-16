package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }
}
