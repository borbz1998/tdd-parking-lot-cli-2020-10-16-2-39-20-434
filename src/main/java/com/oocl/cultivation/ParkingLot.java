package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot {
    private int parkingLotCapacity;
    private Map<ParkingTicket, Car> parkingLotMap = new HashMap<>();

    public ParkingLot(int parkingLotCapacity) {
        this.parkingLotMap = new HashMap<>();
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public ParkingLot() {
    }

    public ParkingTicket park(Car car) {
        if(!isParkingLotMapFull()) {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingLotMap.put(parkingTicket, car);
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)) {
            throw new NoTicketException("Please provide your parking ticket!");
        }
        else if (parkingLotMap.containsKey(parkingTicket)){
            throw new WrongTicketException("Unrecognized Parking Ticket!");
        }
       return parkingLotMap.get(parkingTicket);
    }

    public void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        parkingLotMap.remove(parkingTicket);
    }

    public Boolean isParkingLotMapFull() {
        return parkingLotMap.size() >= parkingLotCapacity;
    }
}
