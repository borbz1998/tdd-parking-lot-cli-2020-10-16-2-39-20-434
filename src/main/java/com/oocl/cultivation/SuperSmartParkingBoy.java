package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{
    private List<Double> parkingLotMapEmptyPosition;
    private int index = 0;

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
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

    public List<Double> getTheParkingLotWithMoreEmptyPosition(List<ParkingLot> parkingLotMapLists, int index) {
        List<Double> availableSpaceList = new ArrayList<>();
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            Double availableSpaceInParking = Double.valueOf(parkingLotMapLists.get(index).getParkingLotCapacity() - parkingLotMapList.getParkingLotMap().size());
            Double parkingLotCapacity = Double.valueOf(parkingLotMapLists.get(index).getParkingLotCapacity());
            availableSpaceList.add(availableSpaceInParking / parkingLotCapacity);
            index++;
        }
        if (Collections.frequency(availableSpaceList, 0.0) == availableSpaceList.size()) {
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
