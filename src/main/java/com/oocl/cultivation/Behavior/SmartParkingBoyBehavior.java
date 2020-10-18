package com.oocl.cultivation.Behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.Exception.NotYourParkingLotException;
import com.oocl.cultivation.Interface.IParkCar;
import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Interface.IParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingLotList;
import com.oocl.cultivation.ParkingTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartParkingBoyBehavior implements IParkCar {

    public ParkingLot parkingLot;
    private List<Integer> parkingLotMapEmptyPosition;
    private int index = 0;

    public SmartParkingBoyBehavior(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public ParkingTicket park(Car car, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        if (isParkingBoyAssignedToParkingLot(iParkingBoy, parkingLotList)) {
            parkingLotMapEmptyPosition = getTheParkingLotWithMoreEmptyPosition(parkingLot.getParkingLotMapLists(), index);
            int maxParkingLotSpaceIndex = parkingLotMapEmptyPosition.indexOf(Collections.max(parkingLotMapEmptyPosition));
            return parkingLot.park(car, parkingLot.getParkingLotMapLists().get(maxParkingLotSpaceIndex).getParkingLotMap());
        }
        throw new NotYourParkingLotException();
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
//                position = new ArrayList<ParkingTicket>(parkingLot.getParkingLotMap().keySet());
//                currentLocation += "ParkingLot Number: " + (index+1) + ", Position: " + (position.indexOf(parkingTicket)+1);
                currentLocation += "ParkingLot Number: " + (index + 1);
            }
            index++;
        }
        return currentLocation;
    }

    public Boolean isParkingBoyAssignedToParkingLot(IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        return parkingLotList.getParkingBoyParkingLotMap().containsKey(iParkingBoy) ? true : false;
    }

}
