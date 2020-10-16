package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkingLotMap = new HashMap<>();

    public ParkingLot(Map<ParkingTicket, Car> parkingLotMap) {
        this.parkingLotMap = parkingLotMap;
    }

    public ParkingLot() {
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLotMap.getOrDefault(parkingTicket, null);
    }

    public void removeCarFromParkingLot(ParkingTicket parkingTicket){
        parkingLotMap.remove(parkingTicket);
    }

    public void checkParkingLotMap(){
//        if(parkingLotMap.size() == park)

    }
}
