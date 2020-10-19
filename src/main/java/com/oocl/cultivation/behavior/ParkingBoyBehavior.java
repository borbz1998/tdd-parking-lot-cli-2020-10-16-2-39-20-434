package com.oocl.cultivation.behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.NoParkingLotSpaceException;
import com.oocl.cultivation.interfaces.IFetchCar;
import com.oocl.cultivation.interfaces.IParkCar;

import java.util.List;

public class ParkingBoyBehavior implements IParkCar, IFetchCar {
    private int index = 0;

    private ParkingLot parkingLot;

    public List<ParkingLot> parkingLotList;

    public ParkingBoyBehavior(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        return parkingLot.park(car, parkingLot.getParkingLotMap());
    }

    public String getCurrentLocation(List<ParkingLot> parkingLotLists, ParkingTicket parkingTicket) {
        StringBuilder currentLocation = new StringBuilder();
        for (ParkingLot parkingLot : parkingLotLists) {
            if (parkingLot.getParkingLotMap().containsKey(parkingTicket)) {
                currentLocation.append("ParkingLot Number: ").append(index + 1);
            }
            index++;
        }
        return currentLocation.toString();
    }

    public ParkingLot getAvailableParkingLot() {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getTheParkingLotWithMoreAvailablePosition() > 0)
                .findFirst()
                .orElseThrow(NoParkingLotSpaceException::new);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        ParkingLot parkingLot = getAvailableParkingLot();
        return parkingLot.fetch(parkingTicket, parkingLotList);
    }
}
