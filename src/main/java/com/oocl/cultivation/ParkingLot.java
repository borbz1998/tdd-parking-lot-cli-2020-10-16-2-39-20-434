package com.oocl.cultivation;

import com.oocl.cultivation.Exception.NoTicketException;
import com.oocl.cultivation.Exception.WrongTicketException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot {

    private int parkingLotCapacity;
    private List<ParkingLot> parkingLotMapLists;
    private Map<ParkingTicket, Car> parkingLotMap;

    public ParkingLot(int parkingLotCapacity) {
        this.parkingLotMap = new HashMap<>();
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public ParkingLot(List<ParkingLot> parkingLotMapLists) {
        this.parkingLotMapLists = parkingLotMapLists;
    }

    public int getParkingLotCapacity() {
        return parkingLotCapacity;
    }

    public List<ParkingLot> getParkingLotMapLists() {
        return parkingLotMapLists;
    }

    public Map<ParkingTicket, Car> getParkingLotMap() {
        return parkingLotMap;
    }

    public ParkingTicket park(Car car, Map parkingLotMap) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        if (isNull(parkingTicket)) {
            throw new NoTicketException();
        }
        Map correctParkingLotMap = isPresentInParkingLotMap(parkingTicket, parkingLotMapLists);
        Car fetchCar = (Car) correctParkingLotMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket, correctParkingLotMap);
        return fetchCar;
    }

    public Map isPresentInParkingLotMap(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            if (parkingLotMapList.getParkingLotMap().containsKey(parkingTicket)) {
                return parkingLotMapList.getParkingLotMap();
            }
        }
        throw new WrongTicketException();
    }

    public void removeCarFromParkingLot(ParkingTicket parkingTicket, Map parkingLotMap) {
        parkingLotMap.remove(parkingTicket);
    }
}
