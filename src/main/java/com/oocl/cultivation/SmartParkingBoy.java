package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    private List<Integer> parkingLotMapEmptyPosition;
    private int index = 0;

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public List<ParkingLot> getParkingLotList() {
        return super.getParkingLotList();
    }

    public ParkingTicket park(Car car) {
        parkingLotMapEmptyPosition = getTheParkingLotWithMoreEmptyPosition(parkingLot.getParkingLotMapLists(), index);
        int maxParkingLotSpaceIndex = parkingLotMapEmptyPosition.indexOf(Collections.max(parkingLotMapEmptyPosition));
        return parkingLot.park(car, parkingLot.getParkingLotMapLists().get(maxParkingLotSpaceIndex).getParkingLotMap());
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        return super.fetch(parkingTicket);
    }

    public List<Integer> getTheParkingLotWithMoreEmptyPosition(List<ParkingLot> parkingLotMapLists, int index) {
        List<Integer> availableSpaceList = new ArrayList<>();
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            availableSpaceList.add(parkingLotMapLists.get(index).getParkingLotCapacity() - parkingLotMapList.getParkingLotMap().size());
            index++;
        }
        if (Collections.frequency(availableSpaceList, 0) == availableSpaceList.size()) {
            throw new NoParkingLotSpaceException();
        }
        return availableSpaceList;
    }

    public String getCurrentLocation(List<ParkingLot> parkingLotLists, ParkingTicket parkingTicket) {
        String currentLocation = "";
        List<ParkingTicket> position;
        for (ParkingLot parkingLot : parkingLotLists) {
            if (parkingLot.getParkingLotMap().containsKey(parkingTicket)) {
                position = new ArrayList<ParkingTicket>(parkingLot.getParkingLotMap().keySet());
//                currentLocation += "ParkingLot Number: " + (index+1) + ", Position: " + (position.indexOf(parkingTicket)+1);
                currentLocation += "ParkingLot Number: " + (index+1);
            }
            index++;
        }
        return currentLocation;
    }
}
