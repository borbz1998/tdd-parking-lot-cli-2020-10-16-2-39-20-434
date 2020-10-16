package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkingLotMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLotMap.containsKey(parkingTicket) ? parkingLotMap.get(parkingTicket) : null;
    }

    public void removeCarFromParkingLot(ParkingTicket parkingTicket){
        parkingLotMap.remove(parkingTicket);
    }
}
