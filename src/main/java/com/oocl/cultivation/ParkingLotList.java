package com.oocl.cultivation;

import com.oocl.cultivation.Interface.IParkingBoy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotList {
    private ParkingLot parkingLot;

    private Map<IParkingBoy, List<ParkingLot>> parkingBoyParkingLotMap = new HashMap<>();

    public ParkingLotList() {
    }

    public Map<IParkingBoy, List<ParkingLot>> getParkingBoyParkingLotMap() {
        return parkingBoyParkingLotMap;
    }

    public void setParkingBoyParkingLotMap(Map<IParkingBoy, List<ParkingLot>> parkingBoyParkingLotMap) {
        this.parkingBoyParkingLotMap = parkingBoyParkingLotMap;
    }
}
