package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoParkingLotSpaceException;
import com.oocl.cultivation.exception.NoTicketException;
import com.oocl.cultivation.exception.WrongTicketException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot {

    private int parkingLotCapacity;

    private Map<ParkingTicket, Car> parkingLotMap;

    public ParkingLot(int parkingLotCapacity) {
        this.parkingLotMap = new HashMap<>();
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public int getParkingLotCapacity() {
        return parkingLotCapacity;
    }

    public Map<ParkingTicket, Car> getParkingLotMap() {
        return parkingLotMap;
    }

    public ParkingTicket park(Car car, Map parkingLotMap) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if (getTheParkingLotWithMoreAvailablePosition() > 0) {
            parkingLotMap.put(parkingTicket, car);
            return parkingTicket;
        } else {
            throw new NoParkingLotSpaceException();
        }
    }

    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        if (isNull(parkingTicket)) {
            throw new NoTicketException();
        }
        Map correctParkingLotMap = checkIfPresentInParkingLotMap(parkingTicket, parkingLotMapLists);
        Car fetchCar = (Car) correctParkingLotMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket, correctParkingLotMap);
        return fetchCar;
    }

    public Map checkIfPresentInParkingLotMap(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
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

    public int getTheParkingLotWithMoreAvailablePosition() {
        return parkingLotCapacity - parkingLotMap.size();
    }

    public Double getTheParkingLotWithMoreAvailablePositionRate() {
        return (double) getTheParkingLotWithMoreAvailablePosition() / (double) parkingLotCapacity;
    }
}
