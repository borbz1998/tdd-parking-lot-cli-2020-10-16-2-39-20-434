package com.oocl.cultivation;

import java.util.ArrayList;
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

    public ParkingTicket park(Car car, List<ParkingLot> parkingLotMapLists) {
        int index = 0;
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            if (!isParkingLotMapFull(parkingLotMapList.getParkingLotMap(), parkingLotMapLists, index)) {
                ParkingTicket parkingTicket = new ParkingTicket();
                parkingLotMapList.getParkingLotMap().put(parkingTicket, car);
                return parkingTicket;
            }
            index++;
        }
        throw new NoParkingLotSpaceException();
    }

//    public ParkingTicket park(Car car, List<ParkingLot> parkingLotMapLists) {
//        for (int i = 0 ; i <= parkingLotMapLists.size() ; i++) {
//            if (!isParkingLotMapFull(parkingLotMap)) {
//                ParkingTicket parkingTicket = new ParkingTicket();
//                parkingLotMap.put(parkingTicket, car);
//                return parkingTicket;
//            }
//        }
//        throw new NoParkingLotSpaceException();
//    }

//    public Car fetch(ParkingTicket parkingTicket) {
//        if (isNull(parkingTicket)) {
//            throw new NoTicketException();
//        } else if (!(parkingLotMap.containsKey(parkingTicket))) {
//            throw new WrongTicketException();
//        }
//        return parkingLotMap.get(parkingTicket);
//    }

    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        if (isNull(parkingTicket)) {
            throw new NoTicketException();
        }

        Map correctParkingLotMap = isPresentInParkingLotMap(parkingTicket, parkingLotMapLists);
        return (Car) correctParkingLotMap.get(parkingTicket);
    }

    public Map isPresentInParkingLotMap(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            if (parkingLotMapList.getParkingLotMap().containsKey(parkingTicket)) {
//                removeCarFromParkingLot(parkingTicket, parkingLotMapLists);
                return parkingLotMapList.getParkingLotMap();
            }
        }
        throw new WrongTicketException();
    }

    // TODO: 10/16/2020  removeCarFromParkingLot must be inside the fetch
    public void removeCarFromParkingLot(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            parkingLotMapList.getParkingLotMap().remove(parkingTicket);
        }
    }

    public Boolean isParkingLotMapFull(Map parkingLotMap, List<ParkingLot> parkingLotMapLists, int index) {
        return parkingLotMap.size() >= parkingLotMapLists.get(index).getParkingLotCapacity();
    }
}
