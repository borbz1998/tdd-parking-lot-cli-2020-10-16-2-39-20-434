package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int parkingLotCapacity;

    private Map<ParkingTicket, Car> parkingLotMap;

    public ParkingLot(int parkingLotCapacity) {
        this.parkingLotMap = new HashMap<>();
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public Map<ParkingTicket, Car> getParkingLotMap() {
        return parkingLotMap;
    }

    public ParkingTicket park(Car car, Map parkingLotMap) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public int getTheParkingLotWithMoreAvailablePosition() {
        return parkingLotCapacity - parkingLotMap.size();
    }

    public Double getTheParkingLotWithMoreAvailablePositionRate() {
        return (double) getTheParkingLotWithMoreAvailablePosition() / (double) parkingLotCapacity;
    }
}
