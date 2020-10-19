package com.oocl.cultivation.behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.NoParkingLotSpaceException;
import com.oocl.cultivation.interfaces.IParkCar;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoyBehavior implements IParkCar {
    private FetchingBehavior fetchingBehavior;
    private List<ParkingLot> parkingLotList;

    public SmartParkingBoyBehavior(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        return parkingLot.park(car, parkingLot.getParkingLotMap());
    }

    public String getCurrentLocation(List<ParkingLot> parkingLotLists, ParkingTicket parkingTicket) {
        int index = 0;
        StringBuffer currentLocation = new StringBuffer();
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
                .max(Comparator.comparing(ParkingLot::getTheParkingLotWithMoreAvailablePosition))
                .orElseThrow(NoParkingLotSpaceException::new);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return this.fetchingBehavior.fetch(parkingTicket);
    }
}
