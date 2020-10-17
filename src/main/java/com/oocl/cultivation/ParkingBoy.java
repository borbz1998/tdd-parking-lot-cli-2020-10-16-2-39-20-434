package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        int index = 0;
        for (ParkingLot parkingLotMapList: parkingLot.getParkingLotMapLists()) {
            if(!isParkingLotMapFull(parkingLotMapList, parkingLot.getParkingLotMapLists(), index)){
                return parkingLot.park(car, parkingLotMapList.getParkingLotMap());
            }
            index++;
        }
        throw new NoParkingLotSpaceException();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

    public Boolean isParkingLotMapFull(ParkingLot parkingLot, List<ParkingLot> parkingLotMapLists, int index) {
        return parkingLot.getParkingLotMap().size() >= parkingLotMapLists.get(index).getParkingLotCapacity();
    }
}
